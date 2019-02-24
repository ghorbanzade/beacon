//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw02;

/**
* This enumeration class provides possible flavors of our ice cream scoops.
*
* @author Pejman Ghorbanzade
* @see    Scoop
*/
public enum Flavor {
  /**
  * Each ice cream scoop may be one of the flavors below.
  */
  VANILLA ("vanilla"),
  CHOCOLATE ("chocolate"),
  STRAWBERRY ("strawberry"),
  LIME ("lime"),
  ORANGE ("orange");

  /**
  * Each enum value will have a string representation. This attribute will
  * be used as part of some of keys to application configuration parameters.
  */
  private final String name;

  /**
  * This private constructor will initialize name of each enumeration with
  * its string literal value.
  *
  * @param name string literal of the flavor enumeration
  */
  private Flavor(String name) {
    this.name = name;
  }

  /**
  * This accessor method will return a string literal of the flavor
  * enumerations.
  *
  * @return a string literal of the flavor of the ice cream
  */
  public String getName() {
    return this.name;
  }
}
