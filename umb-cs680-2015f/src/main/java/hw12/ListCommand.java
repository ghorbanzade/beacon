//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.util.Collections;

/**
* This class defines the functionality of the command to list the
* file system elements that are children of a specified file
* system element.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class ListCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * @param cli active CLI instance user is interacting with
  * @param instruction the instruction instance created based on the user input
  */
  public void execute(Cli cli, Instruction instruction) {
    FileSystemElement target = this.getTarget(cli, instruction);
    ArrayList<FileSystemElement> tray = new ArrayList<FileSystemElement>();
    if (target instanceof Directory) {
      Directory parent = (Directory) target;
      tray.addAll(parent.getChildren());
    } else {
      tray.add(target);
    }
    Collections.sort(tray, cli.getSortMethod());
    this.showResult(tray, instruction);
  }

  /**
  * This helper function determines the directory whose children
  * should be listed.
  *
  * @param cli the CLI instance used to invoke this command
  * @param instruction the instruction instance created based on
  *        the user input
  * @return the file system element whose children should be listed
  */
  private FileSystemElement getTarget(Cli cli, Instruction instruction) {
    FileSystemElement target;
    if (instruction.getArguments().isEmpty()) {
      target = cli.getCurrentDirectory();
    } else {
      String path = instruction.getArguments().get(0);
      target = FileSystem.getInstance().getElementByFullPath(
          cli.getFullPath(path)
      );
    }
    return target;
  }

  /**
  * This helper method determines how a list of elements should
  * be presented to the user, based on options given in the
  * instruction.
  *
  * @param elements the file system elements to be listed
  * @param instruction the instruction instance created based on
  *        the user input
  */
  private void showResult(ArrayList<FileSystemElement> elements,
                          Instruction instruction) {
    ArrayList<String> options = instruction.getOptions();
    if (!elements.isEmpty()) {
      if (options.contains("l")) {
        int totalSize = 0;
        for (FileSystemElement element: elements) {
          totalSize += element.getSize();
        }
        System.out.printf("total %d%n", totalSize);
        for (FileSystemElement element: elements) {
          System.out.printf("%s%n", element);
        }
      } else {
        for (FileSystemElement element: elements) {
          System.out.printf("%s%n", element.getName());
        }
      }
      if (options.contains("R")) {
        for (FileSystemElement element: elements) {
          if (element instanceof Directory) {
            Directory directory = (Directory) element;
            showResult(directory.getChildren(), instruction);
          }
        }
      }
    }
  }
}
