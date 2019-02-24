//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.util.Scanner;

/**
* This class provides the main method of the program.
*
* @author Pejman Ghorbanzade
* @see FileSystem
* @see Cli
*/
public final class FileSystemMain {

  /**
  *
  *
  * @param args command line arguments given to this program.
  */
  public static void main(String[] args) {
    try {
      Scanner input = new Scanner(System.in, "UTF-8");
      Cli cli = new Cli(CliConfig.getInstance().get("cli.name"));
      while (true) {
        try {
          Instruction instruction = getUserInput(input, cli);
          cli.execute(instruction);
        } catch (InvalidCommandException e) {
          System.err.printf("%s%n", e.getMessage());
        } catch (UnsupportedOperationException e) {
          System.err.printf("%s%n", e.getMessage());
        }
      }
    } catch (GracefulTerminationException e) {
      // show logout banner
    } catch (Exception e) {
      System.err.printf("%s%n", e.getMessage());
    }
  }

  /**
  * This method allows user to give a one-liner input to the
  * program. The method returns an instruction object which
  * is not guaranteed to be valid. This instruction is recorded
  * in cli and added to the history of the cli.
  *
  * @param input scanner object to use for getting input
  * @param cli active cli object that user is interacting with
  * @return a user instruction given to the program
  */
  private static Instruction getUserInput(Scanner input, Cli cli) {
    while (true) {
      System.out.printf(cli.getPrompt());
      String strCommand = input.nextLine();
      if (!strCommand.isEmpty()) {
        Instruction instruction = new Instruction(strCommand);
        cli.record(instruction);
        return instruction;
      }
    }
  }

  /**
  * This class must not be instantiated.
  */
  private FileSystemMain() {
  }
}
