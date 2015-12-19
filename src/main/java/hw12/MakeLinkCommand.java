//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.List;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class MakeLinkCommand implements Command {
  /**
  *
  *
  * @param cli
  * @param instruction
  * @throws UnsupportedOperationException
  */
  public void execute(Cli cli, Instruction instruction)
        throws UnsupportedOperationException {
    if (instruction.getArguments().size() == 2) {
      String dst = this.constructPath(cli, instruction.getArguments().get(0));
      String src = this.constructPath(cli, instruction.getArguments().get(1));
      try {
        this.makeLink(cli, dst, src);
      } catch (UnsupportedOperationException e) {
        System.out.printf("%s: failed to create link '%s': %s%n",
            instruction.getName(), instruction.getArguments().get(1), e.getMessage()
        );
      }
    } else {
      String message = String.format("%s: missing operand", instruction.getName());
      throw new UnsupportedOperationException(message);
    }
  }

  /**
  *
  *
  * @param cli
  * @param arg
  * @return
  */
  private String constructPath(Cli cli, String arg) {
    String currentDirectory = cli.getCurrentDirectory().getFullPath();
    if (currentDirectory.equals("/") == false) {
      currentDirectory += "/";
    }
    return arg.startsWith("/") ? arg : currentDirectory.concat(arg);
  }

  /**
  *
  *
  * @param cli
  * @param dst
  * @param src
  */
  private void makeLink(Cli cli, String dst, String src)
      throws UnsupportedOperationException {
    FileSystemElement dstElement =
        FileSystem.getInstance().getElementByFullPath(cli.getFullPath(dst));
    if (dstElement == null) {
      throw new UnsupportedOperationException("Target not exists");
    } else {
      ArrayList<String> srcPath = cli.getFullPath(src);
      List<String> srcParentList = srcPath.subList(0, srcPath.size() - 1);
      FileSystemElement srcParentElement =
          FileSystem.getInstance().getElementByFullPath(srcParentList);
      if (srcParentElement instanceof Directory) {
        Directory srcParentDir = (Directory) srcParentElement;
        String linkName = srcPath.get(srcPath.size() - 1);
        if (srcParentDir.contains(linkName)) {
          throw new UnsupportedOperationException("Element already exists");
        } else {
          srcParentDir.appendChild(new Link(linkName, cli.getUser(), dstElement));
        }
      } else {
        String message = String.format(
            "%s not a directory", srcParentElement.getName()
        );
        throw new UnsupportedOperationException(message);
      }
    }
  }
}
