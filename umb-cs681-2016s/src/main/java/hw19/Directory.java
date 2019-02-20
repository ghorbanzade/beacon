//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;

/**
 * This class defines the directory as a filesystem element that can hold
 * files and other directories.
 *
 * @author Pejman Ghorbanzade
 * @see FileSystemElement
 */
public final class Directory extends FileSystemElement {

  /**
   * A directory has a list in which it holds its immediate children
   * regardless of their type.
   */
  private final ArrayList<FileSystemElement> children;

  /**
   * To create a directory its name and the name of its owner should be
   * specfied.
   *
   * @param name name of the directory
   * @param owner owner of the directory
   */
  public Directory(String name, String owner) {
    super(FileSystemElementType.DIRECTORY, name, owner);
    this.children = new ArrayList<FileSystemElement>();
  }

  /**
   * This method takes a given file system element and adds it to the
   * file system by placing it under this directory.
   *
   * @param element the element to be added under this directory
   */
  public void appendChild(FileSystemElement element) {
    element.setParent(this);
    this.children.add(element);
  }

  /**
   * This method gives access to immediate children of the current directory
   * contained in an arraylist.
   *
   * @return the list of file children of this directory
   */
  public ArrayList<FileSystemElement> getChildren() {
    return this.children;
  }

  /**
   * The total size of a directory is the aggregate of the total size of
   * the file system elements it holds.
   *
   * @return size of the directory
   */
  public int getSize() {
    int size = 0;
    for (FileSystemElement element: this.getChildren()) {
      if (!(element instanceof Link)) {
        size += element.getSize();
      }
    }
    return size;
  }

  /**
   * This method prints information regarding this directory and all the
   * file system elements that can be reached by going down this directory.
   */
  public void showAllElements() {
    System.out.printf("%s:%n", this.getFullPath());
    System.out.printf("total %d%n", this.getSize());
    for (FileSystemElement element: this.getChildren()) {
      System.out.println(element);
    }
    System.out.println();
    for (FileSystemElement element: this.getChildren()) {
      if (element instanceof Directory) {
        ((Directory) element).showAllElements();
      }
    }
  }

}
