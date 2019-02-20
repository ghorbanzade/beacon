//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

/**
* This class defines the functionality of the command for terminating
* the program.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class ExitCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * <p>Based on current implementation, the command will throw
  * GracefulTerminationException regardless of the arguments and options.
  *
  * @param cli the CLI instance we are logging out of
  * @param instruction the instruction instance created based on user input
  * @throws GracefulTerminationException that will break the run-time
  *         loop of the program
  */
  public void execute(Cli cli, Instruction instruction)
              throws GracefulTerminationException {
    throw new GracefulTerminationException();
  }
}
