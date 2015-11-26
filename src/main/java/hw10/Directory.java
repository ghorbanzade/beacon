//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw10;

import java.util.ArrayList;
import java.util.Date;

/**
* This class defines the directory as a filesystem element that can hold
* files and other directories.
*
* @author     Pejman Ghorbanzade
* @see        FSElement
* @see        File
*/
public final class Directory extends FSElement {
  /**
  * Children of a directory are held within an arraylist.
  */
  private final ArrayList<FSElement> children = new ArrayList<FSElement>();

  /**
  * This constructor allows instantiating a new directory by specifying its
  * name and its owner. This design gives liberty for the client to set the
  * date of creation of the directory to any arbitrary time. Although it was
  * possible to allow this time to be set to current instantiation time, this
  * is done so we can have different creation times in the demonstration of
  * the project. As stated in problem, size of the directory is zero.
  *
  * @param name name of the directory.
  * @param owner owner of the directory.
  */
  public Directory(String name, String owner) {
    super(FSElementType.DIRECTORY, name, owner);
  }

  /**
  * This method is used to add a new filesystem element e.g. file or directory
  * to the current directory. The method will set parent of the new filesystem
  * element as the current directory and add the new element to the list of
  * its children.
  *
  * @param element the new filesystem element to be added as children of
            current directory.
  */
  public void appendChild(FSElement element) {
    element.setParent(this);
    this.children.add(element);
  }

  /**
  * This method will give access to immediate children of the current
  * directory, including its files.
  *
  * @return a list of subdirectories and immediate files in current directory
  */
  public ArrayList<FSElement> getChildren() {
    return this.children;
  }

  /**
  * This method gives access to the total size of file system elements within
  * current directory.
  *
  * @return the size of all file system elements within current directory.
  */
  public int getSize() {
    int size = 0;
    for (FSElement element: this.getChildren()) {
      if (element instanceof Link == false) {
        size += element.getSize();
      }
    }
    return size;
  }

  /**
  * This method recursively prints information all the elements within current
  * directory. This method is used to show all elements for the root of the
  * filesystem instance.
  */
  public void showAllElements() {
    System.out.printf("%s:%n", this.getFullPath());
    System.out.printf("total %d%n", this.getSize());
    for (FSElement element: this.getChildren()) {
      System.out.println(element);
    }
    System.out.println();
    for (FSElement element: this.getChildren()) {
      if (element instanceof Directory) {
        ((Directory) element).showAllElements();
      }
    }
  }
}
