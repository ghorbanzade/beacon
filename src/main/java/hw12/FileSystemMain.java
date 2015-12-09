//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.Scanner;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class FileSystemMain {

  /**
  *
  *
  * @param args command line arguments given to this program.
  */
  public static void main(String[] args) {
    FileSystem fs = FileSystem.getInstance();
    ArrayList<Cli> cliList = new ArrayList<Cli>();
    cliList.add(new Cli(CliConfig.getInstance().get("cli.name")));
    try {
      Scanner input = new Scanner(System.in);
      while (true) {
        Cli cli = cliList.get(0);
        System.out.printf(cli.getPrompt());
        String strCommand = input.nextLine();
        if (strCommand.isEmpty()) {
          continue;
        }
        Instruction instruction = new Instruction(strCommand);
        try {
          cli.record(instruction);
          Command command = cli.parse(instruction.getName());
          cli.execute(command, instruction);
        } catch (InvalidCommandException e) {
          System.err.printf("%s%n", e.getMessage());
        } catch (UnsupportedOperationException e) {
          System.err.printf("%s%n", e.getMessage());
        }
      }
    } catch (GracefulTerminationException e) {
      //
    } catch (Exception e) {
      System.err.printf("%s%n", e.getMessage());
    }
  }

  /**
  * This class must not be instantiated.
  */
  private FileSystemMain() {
  }
}
