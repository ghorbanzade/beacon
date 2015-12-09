//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class ListCommand implements Command {
  /**
  *
  *
  * @param cli
  * @param instruction
  */
  public void execute(Cli cli, Instruction instruction) {
    FileSystemElement target = this.getTarget(cli, instruction);
    System.out.printf("%s%n", target);
  }

  /**
  *
  *
  * @param cli
  * @param instruction
  * @return
  */
  private FileSystemElement getTarget(Cli cli, Instruction instruction) {
    FileSystemElement target;
    if (instruction.getArguments().isEmpty()) {
      target = cli.getCurrentDirectory();
    }
    else {
      String path = instruction.getArguments().get(0);
      target = FileSystem.getInstance().getElementByFullPath(cli.getFullPath(path));
    }
    return target;
  }
}
