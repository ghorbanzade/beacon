//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;
import java.util.ArrayList;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class RemoveDirectoryCommand implements Command {
  /**
  *
  *
  * @param cli
  * @param instruction
  * @throws UnsupportedOperationException
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException {
    if (instruction.getArguments().isEmpty()) {
      String message = String.format("rmdir: missing operand");
      throw new UnsupportedOperationException(message);
    } else {
      String currentDirectory = cli.getCurrentDirectory().getFullPath();
      if (currentDirectory.equals("/") == false) {
        currentDirectory += "/";
      }
      boolean caughtException = false;
      for (String arg: instruction.getArguments()) {
        try {
          String path = arg.startsWith("/") ? arg : currentDirectory.concat(arg);
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
