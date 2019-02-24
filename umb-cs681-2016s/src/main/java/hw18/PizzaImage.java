//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw18;

/**
 * This interface allows conversion of a pizza placeholder to a real
 * pizza object according to the proxy design pattern after a certain
 * amount of time which is required for preparation of pizza.
 *
 * @author Pejman Ghorbanzade
 * @see PizzaReal
 * @see PizzaProxy
 */
public interface PizzaImage {

  /**
   * Both pizza placeholder and a real pizza should specify what should
   * happen when client asks for his pizza.
   *
   * @return the name of the pizza client has ordered
   */
  public abstract String getPizza();

}
