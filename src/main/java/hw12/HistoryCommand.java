//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
* This class defines the functionality of the command for printing
* a number of most recent user-issued instructions.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class HistoryCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * <p>Based on current implementation, regardless of the number
  * of arguments and options, this command always prints all the
  * instructions stored in the history data structure.
  *
  * @param cli the CLI instance whose instructions should be printed
  * @param instruction the instruction instance created based on the user input
  */
  public void execute(Cli cli, Instruction instruction) {
    SimpleDateFormat df = new SimpleDateFormat("MMM dd kk:mm:ss", Locale.US);
    for (Instruction instruct: cli.getHistory().getInstructions()) {
      System.out.printf("%s %s%n",
                        df.format(instruct.getDate()), instruct);
    }
  }
}
