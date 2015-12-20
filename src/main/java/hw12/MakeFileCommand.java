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
public final class MakeFileCommand implements Command {
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
      String message = String.format("%s: missing operand", instruction.getName());
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
          this.makeFile(cli, instruction, arg);
        } catch (UnsupportedOperationException e) {
          System.out.printf("%s: failed to create '%s': %s%n",
              instruction.getName(), arg, e.getMessage()
          );
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

  /**
  *
  *
  * @param
  * @param
  * @param
  * @return
  */
  private void makeFile(Cli cli, Instruction instruction, String path) {
    ArrayList<String> dirs = cli.getFullPath(path);
    FileSystemElement element = FileSystem.getInstance()
        .getElementByFullPath(dirs.subList(0, dirs.size() - 1));
    if (element instanceof Directory) {
      Directory dir = (Directory) element;
      String newFileName = dirs.get(dirs.size() - 1);
      FileSystemElement child = dir.getChild(newFileName);
      if (child == null) {
        dir.appendChild(
            new File(newFileName, cli.getUser().getUsername(), 100)
        );
      } else {
        // gracefully ignore operation.
      }
    } else {
      throw new UnsupportedOperationException("Not a directory");
    }
  }
}
