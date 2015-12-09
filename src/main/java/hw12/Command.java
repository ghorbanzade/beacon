//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

/**
*
*
* @author Pejman Ghorbanzade
*/
public interface Command {
  /**
  *
  *
  * @param cli
  * @param instruction
  * @throws UnsupportedOperationException
  * @throws GracefulTerminationException
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException, GracefulTerminationException;
}
