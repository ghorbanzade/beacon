//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

/**
* This custom exception class provides the expected way to terminate the
* run time loop of the program. By throwing an instance of this
* exception class the client can terminate the program.
*
* @author Pejman Ghorbanzade
* @see FileSystemMain
* @see ExitCommand
*/
public class GracefulTerminationException extends Exception {
  /**
  * This constructor does nothing other than calling the super
  * constructor.
  */
  public GracefulTerminationException() {
    super();
  }
}
