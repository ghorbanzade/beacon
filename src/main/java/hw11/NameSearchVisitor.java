//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw11;

import java.util.ArrayList;

/**
* This visitor class defines the policy to search a file system for elements
* that contain a specified phrase.
*
* @author Pejman Ghorbanzade
* @see FileSystemVisitor
*/
public final class NameSearchVisitor implements FileSystemVisitor {
  /**
  * Each name search visitor has a phrase to look for and a list of
  * file system elements that contains the elements whose name contains
  * the given phrase.
  */
  private String key;
  private ArrayList<FileSystemElement> result = new ArrayList<FileSystemElement>();

  /**
  * Upon instantiation, a name search visitor should know what phrase it is
  * supposed to look for in the file system.
  *
  * @param key the phrase we are going to search for in the file system
  */
  public NameSearchVisitor(String key) {
    this.key = key;
  }

  /**
  * As any other file system element, a file is a match if its name contains
  * the queried phrase.
  *
  * @param file the file whose name should be checked
  */
  public void visit(File file) {
    search(file);
  }

  /**
  * As any other file system element, a directory is a match if its name
  * contains the queried phrase.
  *
  * @param directory the directory whose name should be checked
  */
  public void visit(Directory directory) {
    search(directory);
  }

  /**
  * As any other file system element, a link is a match if its name contains
  * the queried phrase.
  * Note that the target of the link is not looked into.
  *
  * @param link the link whose name should be checked
  */
  public void visit(Link link) {
    search(link);
  }

  /**
  * This helper method will check the name of file system element for a
  * match with the given phrase.
  *
  * @param element the file system element whose name should be checked
  */
  private void search(FileSystemElement element) {
    if (element.getName().contains(this.key)) {
      result.add(element);
    }
  }

  /**
  * This method will return the array list containing file system elements
  * whose name is a match for the queried phrase.
  *
  * @return the arraylist containing the result
  */
  public ArrayList<FileSystemElement> getResult() {
    return this.result;
  }

  /**
  * This method will return a ready-to-print string literal containing
  * information about all file system elements in the result list.
  *
  * @return the string literal representing the result
  */
  public String showResult() {
    StringBuilder sb = new StringBuilder();
    for (FileSystemElement res: this.result) {
      sb.append(String.format("%n%s", res));
    }
    return sb.toString();
  }
}
