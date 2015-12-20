//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;

/**
* This class defines the functionality of the command for handling
* multiple CLI objects.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class CliCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the parent Command class.
  *
  * <p>Based on current implementation, the command accepts no
  * argument and returns the name of the CLI it is invoked on.
  *
  * @param cli the CLI instance whose active directory should be changed
  * @param instruction the instruction instance created based on user input
  * @throws UnsupportedOperationException in case argument is
  *         given to the command 
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException {
    if (instruction.getArguments().size() == 0) {
      System.out.printf("%s%n", cli.getName());
    } else {
      throw new UnsupportedOperationException();
    }
  }
}
