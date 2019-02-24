//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

/**
* This interface provides a contract for all CLI commands.
*
* @author Pejman Ghorbanzade
* @see Cli
*/
public interface Command {
  /**
  * Any class implementing this interface must indicate how to execute
  * a command.
  *
  * @param cli the CLI instance whose active directory should be changed
  * @param instruction the instruction instance created based on user input
  * @throws UnsupportedOperationException in case the instruction given
  *         to execute the command is not in proper form to execute it.
  * @throws GracefulTerminationException in case the given instruction
  *         requires controlled termination of the program.
  */
  public void execute(Cli cli, Instruction instruction) throws
      UnsupportedOperationException, GracefulTerminationException;
}
