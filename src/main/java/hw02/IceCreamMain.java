//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw02;

/**
* This class demonstrates how to use the proposed design to make an ice cream.
*
* @author Pejman Ghorbanzade
*/
public class IceCreamMain {
  /**
  * This main method demonstrates how to make an ice cream using the proposed
  * design. Since some methods may throw exceptions depending on the order and
  * the number of times they are called, all related methods are wrapped in a
  * try-catch block.
  *
  * @param args command line arguments to be given to the program
  */
  public static void main(String[] args) {
    try {
      Cone cone = new Cone(Size.MEDIUM);
      cone.setTopping(Topping.EXTRACHOCOLATE);
      cone.addScoop(new Scoop(Flavor.CHOCOLATE));
      cone.getScoops().get(0).addTopping(Topping.NUTS);
      cone.addScoop(new Scoop(Flavor.CHOCOLATE));
      cone.getScoops().get(0).addScoop(Flavor.VANILLA);
      cone.getScoops().get(0).getUpperLayer().addScoop(Flavor.VANILLA);
      System.out.print(cone);
    } catch (IceCreamException e) {
      e.printStackTrace();
    }
  }
}
