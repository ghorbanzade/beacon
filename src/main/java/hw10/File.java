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
* This class defines the file as a filesystem element with no children.
*
* @author     Pejman Ghorbanzade
* @see        FSElement
* @see        Directory
*/
public final class File extends FSElement {
  /**
  * Construcor of a file object.
  *
  * @param name name of the file
  * @param owner owner of the file
  * @param created date at which the file is created
  * @param size size of the file
  */
  public File(String name, String owner, Date created, int size) {
    super(name, owner, created, size);
  }
}
