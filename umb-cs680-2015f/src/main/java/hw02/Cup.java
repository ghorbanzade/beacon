//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw02;

/**
* The Cup class defines a Container type that can hold the ice cream scoops.
* A cup has a size and depending on its size, has a price that is specified
* in application configuration file.
*
* @author   Pejman Ghorbanzade
* @see      Container
* @see      Cone
*/
public final class Cup extends Container {
  /**
  * The client can order different sizes for Cup. Once the size is given,
  * the price of cup is calculated based on information in configuration file.
  *
  * @param size the size of a cup
  */
  public Cup(Size size) {
    super("cup", size);
  }
}
