//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.util.List;

/**
* This class defines the functionality of the command for creating
* a new link in the file system.
*
* @author Pejman Ghorbanzade
* @see Command
* @see Cli
*/
public final class MakeLinkCommand implements Command {
  /**
  * This method provides the functionality of the command by
  * implementing execute method of the Command interface.
  *
  * <p>Based on current implementation, this command accepts two
  * arguments which are paths (relative or absolute) to target and
  * new link, respectively.
  *
  * @param cli the CLI instance used to create the link
  * @param instruction the instruction instance created based on the user input
  * @throws UnsupportedOperationException in case a non-existant
  *         file system is specified as target element and the path
  *         to new link is valid
  */
  public void execute(Cli cli, Instruction instruction)
      throws UnsupportedOperationException {
    if (instruction.getArguments().size() == 2) {
      String dst = cli.toFullPath(instruction.getArguments().get(0));
      String src = cli.toFullPath(instruction.getArguments().get(1));
      try {
        this.makeLink(cli, dst, src);
      } catch (UnsupportedOperationException e) {
        System.out.printf("%s: failed to create link '%s': %s%n",
            instruction.getName(), instruction.getArguments().get(1),
            e.getMessage()
        );
      }
    } else {
      String message = String.format("%s: missing operand", instruction.getName());
      throw new UnsupportedOperationException(message);
    }
  }

  /**
  * This helper method takes full paths of target and location of
  * the new link element and creates the link element in case the
  * target exists and all ancestors of the new link exist.
  *
  * @param cli the CLI instance used to create the new link
  * @param dst the full path to target element
  * @param src the full path to the new link
  * @throws UnsupportedOperationException in case a non-existant
  *         path is given for the target of the link
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
          srcParentDir.appendChild(
              new Link(linkName, cli.getUser().getUsername(), dstElement)
          );
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
