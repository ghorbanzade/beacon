//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;

/**
* This class defines the functionality of the command for making a
* new directory in the file system.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class RemoveDirectoryCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * <p>Based on current implementation, the user may remove
  * multiple directories by providing multiple arguments. These
  * arguments may be in form of relative or absolute paths.
  *
  * @param cli the CLI instance used to remove directories
  * @param instruction the instruction instance created based
  *        on the user input
  * @throws UnsupportedOperationException in case the specified
  *         target directory does not exist or has a non-existant
  *         ancestor.
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException {
    if (instruction.getArguments().isEmpty()) {
      String message = String.format("rmdir: missing operand");
      throw new UnsupportedOperationException(message);
    } else {
      boolean caughtException = false;
      for (String arg: instruction.getArguments()) {
        try {
          String path = cli.toFullPath(arg);
          FileSystemElement target =
              FileSystem.getInstance().getElementByFullPath(cli.getFullPath(path));
          if (target instanceof Directory) {
            Directory targetDir = (Directory) target;
            if (targetDir.getChildren().isEmpty()) {
              targetDir.getParent().getChildren().remove(targetDir);
            } else {
              throw new UnsupportedOperationException("Directory not empty");
            }
          } else {
            throw new UnsupportedOperationException("Not a directory");
          }
        } catch (UnsupportedOperationException e) {
          String message = String.format(
              "%s: failed to remove '%s': %s",
              instruction.getName(), arg, e.getMessage()
          );
          System.out.println(message);
          caughtException = true;
        }
      }
      if (caughtException) {
        String message = String.format(
            "%s: command failed for at least one argument",
            instruction.getName()
        );
        throw new UnsupportedOperationException(message);
      }
    }
  }
}
