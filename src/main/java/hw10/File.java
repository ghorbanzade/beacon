//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw10;

import java.util.ArrayList;

/**
* This class defines the file as a filesystem element with no children.
*
* @author     Pejman Ghorbanzade
* @see        FSElement
* @see        Directory
*/
public final class File extends FSElement {
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
    super(FSElementType.FILE, name, owner);
    this.size = size;
  }

  /**
  * This method implements getSize() method of FSElement object by simply
  * returning the size of the file.
  *
  * @return size of the file
  */
  public int getSize() {
    return this.size;
  }

}
