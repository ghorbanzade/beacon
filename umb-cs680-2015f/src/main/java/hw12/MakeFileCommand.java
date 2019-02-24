//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;

/**
* This class defines the functionality of the command for creating
* a new file in the file system.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class MakeFileCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * <p>Based on current implementation, this command accepts arbitrary
  * number of arguments and tries to create a file for each argument.
  *
  * @param cli the CLI instance whose active directory should be printed
  * @param instruction the instruction instance created based on the user input
  * @throws UnsupportedOperationException in case the provided instruction
  *         is not in proper form to execute the command
  */
  public void execute(Cli cli, Instruction instruction)
      throws UnsupportedOperationException {
    if (instruction.getArguments().isEmpty()) {
      String message = String.format(
          "%s: missing operand", instruction.getName()
      );
      throw new UnsupportedOperationException(message);
    } else {
      boolean caughtException = false;
      for (String arg: instruction.getArguments()) {
        try {
          this.makeFile(cli, arg);
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
  * This method takes an argument in form of a full path to the new
  * file system element and creates a new file system element
  * in that given path.
  *
  * @param cli the CLI instance used to create an active directory
  * @param arg the argument given for the command
  */
  private void makeFile(Cli cli, String arg)
      throws UnsupportedOperationException {
    String path = cli.toFullPath(arg);
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
      }
    } else {
      throw new UnsupportedOperationException("Not a directory");
    }
  }
}
