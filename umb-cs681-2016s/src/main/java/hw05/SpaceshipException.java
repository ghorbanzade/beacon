//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw05;

/**
 * This exception is thrown when the client performs an invalid operation
 * during the game.
 *
 * @author Pejman Ghorbanzade
 * @see Spaceship
 */
public class SpaceshipException extends Exception {
  /**
   * This exception throws a simple message if user inputs lead to scenarios
   * in which program should gracefully terminate.
   *
   * @param message the message to be printed when exception is thrown
   */
  public SpaceshipException(String message) {
    super(message);
  }
}
