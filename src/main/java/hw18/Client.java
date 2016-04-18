//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw18;
 
import java.util.Arrays;

/**
 * This class defines a client (which is passed to a thread) as an
 * agent that asks for a number of pizzas, wait until they are ready
 * and leaves the store either with his orders or without in case his
 * patience is ran out.
 *
 * @author Pejman Ghorbanzade
 * @see Pizza
 */
public final class Client implements Runnable {

  /**
   * A client has an id and a pizza proxy which can either be his order
   * or a placeholder for his order.
   */
  private final String id;
  private final ConfigReader cr;
  private final PizzaProxy proxy;

  /**
   * A client is an object with a specific id and characteristics
   * specified in the program configuration file.
   */
  public Client() {
    this.cr = new ConfigReader("/pizza.properties");
    this.proxy = new PizzaProxy();
    this.id = new Identifier().get();
    this.log("entered shop");
  }

  /**
   * This method allows the client to place an order for a pizza with a
   * given name.
   *
   * @param name the name of the pizza client is ordering
   * @return an instance of the pizza image interface which is either a
   *         pizza proxy or a real pizza
   */
  public PizzaImage order(String name) {
    new Thread(()-> this.proxy.setRealPizza(new PizzaReal(name))).start();
    return this.proxy;
  }

  /**
   * This utility method prints a message regarding the client activities
   * along with its id.
   *
   * @param args the formatted arguments to be passed to print stream
   */
  public void log(Object... args) {
    Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
    String message = String.format((String) args[0], arguments);
    System.out.printf("client %s: %s.%n", this.id, message);
  }

  /**
   * This method defines what should be done once the thread to which
   * a client is passed is started.
   *
   * <p>When the thread starts, the client looks up the menu of the
   * store and orders a number of pizzas (according to program config)
   * and then waits for his pizza to be ready. The client periodically
   * inquires about his order until it is either ready or he runs out
   * of patience in which case he simply leaves the store.
   */
  public void run() {
    MenuReader mr = new MenuReader(cr.get("pizza.names.file"));
    long patience = Long.parseLong(this.cr.get("clients.patience"));
    long tolerance = Long.parseLong(this.cr.get("clients.tolerance"));
    this.log("starts to wait to pick up orders");
    PizzaImage p1 = this.order(mr.get());
    long tic = System.currentTimeMillis();
    while (true) {
      try {
        Thread.sleep(patience);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      this.log("asks if his pizza is ready");
      if (((PizzaProxy) p1).isReady()) {
        this.log("picked up his %s pizza", p1.getPizza());
        break;
      }
      if (tic + tolerance < System.currentTimeMillis()) {
        this.log("left the store without order");
        break;
      }
    }

  }

  /**
   * This method allows other classes to access id of the client object
   * they are passed to, for printing purposes.
   *
   * @return the id assigne to this client object
   */
  public String getId() {
    return this.id;
  }

}
