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
public final class CliCommand implements Command {
  /**
  *
  *
  * @param cli
  * @param instruction
  * @throws UnsupportedOperationException
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException {
    if (instruction.getArguments().size() == 0) {
      System.out.printf("%s%n", cli.getName());
    } else {
      throw new UnsupportedOperationException();
    }
  }
}
