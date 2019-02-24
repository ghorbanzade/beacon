//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw03;

/**
 * This exception is thrown when the client performs an invalid operation when
 * handling polygons.
 *
 * @author Pejman Ghorbanzade
 * @see Polygon
 */
public class PolygonException extends Exception {
  /**
   * This exception throws a simple message if application methods are called
   * in improper sequence or are given invalid inputs.
   *
   * @param message the message to be printed when exception is thrown
   */
  public PolygonException(String message) {
    super(message);
  }
}
