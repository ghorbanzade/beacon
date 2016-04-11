//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw18;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see Pizza
 * @see PizzaProxy
 */
public class PizzaReal implements PizzaImage {

  /**
   *
   */
  private final String name;
  private final ConfigReader cr = new ConfigReader("/pizza.properties");

  /**
   *
   *
   * @param name
   */
  public PizzaReal(String name) {
    int time = Integer.parseInt(this.cr.get("pizza.preparation.time"));
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("announcement: %s is ready.%n", name);
    this.name = name;
  }

  /**
   *
   *
   * @return
   */
  public String getPizza() {
    return this.name;
  }

}
