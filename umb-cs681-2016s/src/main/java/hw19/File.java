//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;

/**
 * This class defines a file as a file system element that has a
 * fixed size.
 *
 * @author Pejman Ghorbanzade
 * @see FileSystemElement
 */
public final class File extends FileSystemElement {

  /**
   * A file is the only file system element which has an actual size.
   * Based on current design, the size of the file cannot be changed
   * once it is constructed.
   */
  private final int size;

  /**
   * To create a new file, its name, its owner and its size must be
   * provided.
   *
   * @param name name of the file
   * @param owner owner of the file
   * @param size size of file
   */
  public File(String name, String owner, int size) {
    super(FileSystemElementType.FILE, name, owner);
    this.size = size;
  }

  /**
   * This method allows access to the size of the file for information
   * purposes.
   *
   * @return the size of the file
   */
  public int getSize() {
    return this.size;
  }

}
