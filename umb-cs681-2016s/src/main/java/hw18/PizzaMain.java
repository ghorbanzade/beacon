//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw18;
 
/**
 * This class demonstrate a multi-threaded design for a pizza
 * store where every client is a thread which orders a pizza
 * object from pizza image interface which will change into a
 * real pizza after a certain amount of time.
 *
 * @author Pejman Ghorbanzade
 */
public final class PizzaMain {

  /**
   * This program allows a number of clients to enter a pizza
   * store, order a number of pizzas and leave the store when
   * either their orders are ready for pickup or they run out
   * of patience.
   *
   * <p>All configuration parameters such as the number of clients,
   * the number of pizzas to order, the time after which a client
   * asks for his order and the time after which he goes leaves the
   * store (even if his pizza is not ready yet) is indicated
   * in a configuration file which is parsed using a ConfigReader
   * object.
   *
   * @param args command line arguments given to this program
   */
  public static void main(String[] args) {
    ConfigReader cr = new ConfigReader("/pizza.properties");
    int numi = Integer.parseInt(cr.get("clients.number"));
    for (int i = 0; i < numi; i++) {
      new Thread(new Client()).start();
    }
  }

  /**
   * This class shall not be instantiated.
   */
  private PizzaMain() {
  }

}
