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
 */
public final class PizzaMain {

  /**
   *
   *
   * @param args
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
