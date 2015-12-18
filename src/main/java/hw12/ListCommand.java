//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.util.Collections;

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
    ArrayList<FileSystemElement> tray = new ArrayList<FileSystemElement>();
    if (target instanceof Directory) {
      Directory parent = (Directory) target;
      tray.addAll(parent.getChildren());
    } else {
      tray.add(target);
    }
    Collections.sort(tray, cli.getSortMethod());
    this.showResult(tray, instruction.getOptions());
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

  /**
  *
  *
  * @param elements
  * @param options
  */
  private void showResult(ArrayList<FileSystemElement> elements,
                          ArrayList<String> options) {
    if (elements.isEmpty() == false) {
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
            showResult(directory.getChildren(), options);
          }
        }
      }
    }
  }
}
