//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

/**
* This class defines the functionality of the command for changing
* current directory of the active cli object.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class ChangeDirectoryCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the parent Command class.
  *
  * <p>Based on current implementation, the command accepts zero or
  * one argument. In case no argument is provided, the command
  * will return the current active directory of passed cli object
  * to the default directory. Otherwise, regardless of the number
  * of arguments, the command will try to change current directory
  * to the Directory the first argument points to.
  *
  * @param cli the CLI instance whose active directory should be changed
  * @param instruction the instruction instance created based on user input
  * @throws UnsupportedOperationException in case the specified
  *         target directory does not exist
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException {
    String path = instruction.getArguments().isEmpty()
        ? CliConfig.getInstance().get("cli.homedir")
        : instruction.getArguments().get(0);
    FileSystemElement element =
        FileSystem.getInstance().getElementByFullPath(cli.getFullPath(path));
    if (element instanceof Directory) {
      cli.setCurrentDirectory((Directory) element);
    } else {
      throw new UnsupportedOperationException(path + ": Not a directory");
    }
  }
}
