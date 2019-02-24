//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw18;

/**
 * This class provides an implementation of pizza image which defines
 * what a real pizza is.
 *
 * @author Pejman Ghorbanzade
 * @see PizzaProxy
 * @see PizzaReal
 */
public class PizzaReal implements PizzaImage {

  /**
   * A real pizza has a name.
   */
  private final String name;

  /**
   * The constructor takes the name of a pizza to prepare and waits until
   * the requested pizza is prepared. The name of the order is assigned
   * only after the pizza is out of the oven.
   *
   * @param name name of the pizza according to the menu
   */
  public PizzaReal(String name) {
    ConfigReader cr = new ConfigReader("/pizza.properties");
    int time = Integer.parseInt(cr.get("pizza.preparation.time"));
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("announcement: %s is ready.%n", name);
    this.name = name;
  }

  /**
   * This method prints the name of the pizza which is by now ready for
   * delivery.
   *
   * @return name of the pizza the client has ordered
   */
  @Override
  public String getPizza() {
    return this.name;
  }

}
