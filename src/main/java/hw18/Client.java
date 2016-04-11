//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw18;
 
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see Pizza
 */
public final class Client implements Runnable {

  /**
   *
   */
  private final String id;
  private final ConfigReader cr;
  private final PizzaProxy proxy;

  /**
   *
   */
  public Client() {
    this.cr = new ConfigReader("/pizza.properties");
    this.proxy = new PizzaProxy();
    this.id = new Identifier().get();
    this.log("entered shop");
  }

  /**
   *
   *
   * @param name
   * @return
   */
  public PizzaImage order(String name) {
    new Thread(()-> this.proxy.setRealPizza(new PizzaReal(name))).start();
    return this.proxy;
  }

  /**
   *
   *
   * @param args
   */
  public void log(Object... args) {
      Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
      String message = String.format((String) args[0], arguments);
      System.out.printf("client %s: %s.%n", this.id, message);
  }

  /**
   *
   */
  public void run() {
    MenuReader mr = new MenuReader(cr.get("pizza.names.file"));
    long patience = Long.parseLong(this.cr.get("clients.patience"));
    long tolerance = Long.parseLong(this.cr.get("clients.tolerance"));
    int numj = Integer.parseInt(this.cr.get("orders.per.client"));

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
   *
   *
   * @return
   */
  public String getId() {
    return this.id;
  }

}
