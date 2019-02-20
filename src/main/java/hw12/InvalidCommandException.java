//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

/**
* This custom exception class will be thrown in case the user-specified
* command is deemed insufficient to successfully execute the command.
*
* @author Pejman Ghorbanzade
*/
public class InvalidCommandException extends Exception {
  /**
  * Any instance of this exception class will have a message that
  * can be used for logging and debugging purposes.
  *
  * @param message the message to be printed when exception is thrown
  */
  public InvalidCommandException(String message) {
    super(message);
  }
}
