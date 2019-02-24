//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw02;

/**
* This enumeration class provides possible sizes for containers that hold
* ice cream scoops.
*
* @author   Pejman Ghorbanzade
* @see      Container
* @see      Cone
* @see      Cup
*/
public enum Size {
  /**
  * Each container comes in three sizes of small, medium and large.
  */
  SMALL ("small"),
  MEDIUM ("medium"),
  LARGE ("large");

  /**
  * Each enum value will have a string representation. This attribute will
  * be used as part of some of keys to application configuration parameters.
  */
  private final String name;

  /**
  * This private constructor will initialize name of each enumeration with
  * its string literal value.
  *
  * @param name string literal describing the size of the container
  */
  private Size(String name) {
    this.name = name;
  }

  /**
  * This accessor method will return a string literal of the size enumerations.
  *
  * @return a string literal of the size of the container
  */
  public String getName() {
    return this.name;
  }
}
