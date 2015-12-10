//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class ChangeDirectoryCommand implements Command {
  /**
  *
  *
  * @param cli
  * @param instruction
  * @throws UnsupportedOperationException
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
