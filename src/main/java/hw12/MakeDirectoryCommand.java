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
* This class defines the functionality of the command for making a
* new directory in the file system.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class MakeDirectoryCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * <p>Based on current implementation, the user may create
  * multiple directories by providing multiple arguments. These
  * arguments may be in form of relative or absolute paths.
  *
  * @param cli the CLI instance used to create new directories
  * @param instruction the instruction instance created based
  *        on the user input
  * @throws UnsupportedOperationException in case the specified
  *         target directory has a non-existant ancestor
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException {
    if (instruction.getArguments().isEmpty()) {
      String message = String.format("%s: missing operand", instruction.getName());
      throw new UnsupportedOperationException(message);
    } else {
      boolean caughtException = false;
      for (String arg: instruction.getArguments()) {
        try {
          this.makeDirectory(cli, arg);
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
  * This helper method creates a new directory based on a given
  * relative or absolute path.
  *
  * @param cli the CLI instance used for creating a new directory
  * @param arg a relative or absolute path to new directory
  * @throws UnsupportedOperationException in case the specified
  *         target directory has a non-existant ancestor
  */
  private void makeDirectory(Cli cli, String arg)
      throws UnsupportedOperationException {
    String path = cli.toFullPath(arg);
    ArrayList<String> dirs = cli.getFullPath(path);
    FileSystemElement element = FileSystem.getInstance()
        .getElementByFullPath(dirs.subList(0, dirs.size() - 1));
    if (element instanceof Directory) {
      Directory dir = (Directory) element;
      String newDirName = dirs.get(dirs.size() - 1);
      FileSystemElement child = dir.getChild(newDirName);
      if (child == null) {
        dir.appendChild(
            new Directory(newDirName, cli.getUser().getUsername())
        );
      } else {
        String message = "Element already exists";
        throw new UnsupportedOperationException(message);
      }
    } else {
      throw new UnsupportedOperationException("Not a directory");
    }
  }
}
