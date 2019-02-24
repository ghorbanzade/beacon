//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw11;

import java.util.ArrayList;
import java.util.Date;

/**
* This class defines the file as a filesystem element with no children.
*
* @author Pejman Ghorbanzade
* @see FileSystemElement
* @see Directory
*/
public final class File extends FileSystemElement {
  /**
  * Each file has a size. Based on problem requirements, the size of a
  * file is fixed.
  */
  private final int size;

  /**
  * Construcor of a file object.
  *
  * @param name name of the file
  * @param owner owner of the file
  * @param size size of the file
  */
  public File(String name, String owner, int size) {
    super(FileSystemElementType.FILE, name, owner);
    this.size = size;
  }

  /**
  * This method implements getSize() method of FileSystemElement object by simply
  * returning the size of the file.
  *
  * @return size of the file
  */
  public int getSize() {
    return this.size;
  }

  /**
  * This method will take any subclass of the FileSystemVisitor class and
  * executes its algorithm on the current file.
  *
  * @param visitor an object from a subclass of FileSystemVisitor class.
  */
  public void accept(FileSystemVisitor visitor) {
    visitor.visit(this);
  }

}
