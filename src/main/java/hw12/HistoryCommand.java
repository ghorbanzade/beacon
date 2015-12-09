//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class HistoryCommand implements Command {
  /**
  *
  *
  * @param cli
  * @param instruction
  */
  public void execute(Cli cli, Instruction instruction) {
    SimpleDateFormat df = new SimpleDateFormat("MMM dd kk:mm:ss", Locale.US);
    for (Instruction instruct: cli.getHistory().getInstructions()) {
      System.out.printf("%s. %s %s%n", instruct.getId(),
          df.format(instruct.getDate()), instruct);
    }
  }
}
