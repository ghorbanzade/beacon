//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw08;

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
