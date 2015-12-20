//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;

/**
* This class defines the functionality of the command for printing
* current directory of the active cli object.
*
* @author Pejman Ghorbanzade
*/
public final class CurrentDirectoryCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * <p>Based on current implementation, regardless of the number
  * of arguments and options, this command always prints full path
  * of the current directory of the active cli object.
  *
  * @param cli the CLI instance whose active directory should be printed
  * @param instruction the instruction instance created based on the user input
  */
  public void execute(Cli cli, Instruction instruction) {
    System.out.printf("%s%n", cli.getCurrentDirectory().getFullPath());
  }
}
