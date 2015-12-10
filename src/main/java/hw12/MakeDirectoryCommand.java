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
public final class MakeDirectoryCommand implements Command {
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
      String message = String.format("%s: missing operand",
                      instruction.getName());
      throw new UnsupportedOperationException(message);
    } else {
      String arg = instruction.getArguments().get(0);
      // TODO: mkdir dir1/dir2 should create dir2 inside dir1
      if (cli.getCurrentDirectory().contains(arg)) {
        String message = String.format(
                        "%s: directory %s exists",
                        instruction.getName(), arg);
        throw new UnsupportedOperationException(message);
      } else {
        cli.getCurrentDirectory().appendChild(new Directory(arg, cli.getUser()));
      }
    }
  }
}
