//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw02;

/**
* This exception is thrown when the client performs an invalid operation when
* making an ice cream.
*
* @author     Pejman Ghorbanzade
* @see        IceCreamConfig
*/
public class IceCreamException extends Exception {
  /**
  * This constructor allows to set the message to be shown when
  * the exception is thrown.
  *
  * @param message the message to be shown for the exception
  */
  public IceCreamException(String message) {
    super(message);
  }
}
