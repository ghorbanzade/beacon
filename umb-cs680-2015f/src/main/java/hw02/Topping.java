//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw02;

/**
* An enumeration class for different toppings that can be supported for our
* scoops. Interestingly, toppings can also be added on the cone!
*
* @author Pejman Ghorbanzade
* @see Scoop
* @see Cone
*/
public enum Topping {
  /**
  * An ice cream scoop can have one or multiple of the following toppings.
  */
  NUTS ("nuts"),
  EXTRACHOCOLATE ("extra-chocolate"),
  STRAWBERRYJEERRY ("strawberry-jeerry");

  /**
  * Each enum value will have a string representation. This attribute will
  * be used as part of some of keys to application configuration parameters.
  */
  private final String name;

  /**
  * This private constructor will initialize name of each enumeration with
  * its string literal value.
  *
  * @param name string literal of the topping enumeration
  */
  private Topping(String name) {
    this.name = name;
  }

  /**
  * This accessor method will return a string literal of the topping
  * enumeration.
  *
  * @return a string literal of the topping of the scoop or the cone
  */
  public String getName() {
    return this.name;
  }
}
