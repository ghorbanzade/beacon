//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;
import java.util.ArrayList;

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
      String message = String.format("mkdir: missing operand");
      throw new UnsupportedOperationException(message);
    } else {
      String currentDirectory = cli.getCurrentDirectory().getFullPath();
      if (currentDirectory.equals("/") == false) {
        currentDirectory += "/";
      }
      for (String arg: instruction.getArguments()) {
        String path = arg.startsWith("/") ? arg : currentDirectory.concat(arg);
        ArrayList<String> dirs = cli.getFullPath(path);
        FileSystemElement element = FileSystem.getInstance()
            .getElementByFullPath(dirs.subList(0, dirs.size() - 1));
        if (element instanceof Directory) {
          Directory dir = (Directory) element;
          String newDirName = dirs.get(dirs.size() - 1);
          FileSystemElement child = dir.getChild(newDirName);
          if (child == null) {
            dir.appendChild(new Directory(newDirName, cli.getUser()));
          } else if (child instanceof Directory) {
            String message = String.format("%s already exists", child.getName());
            throw new UnsupportedOperationException(message);
          } else {
            dir.appendChild(new Directory(newDirName, cli.getUser()));
          }
        } else {
          String message = String.format("%s not a directory", element.getName());
          throw new UnsupportedOperationException(message);
        }
      }
    }
  }
}
