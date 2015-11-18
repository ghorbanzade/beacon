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
*
*
* @author     Pejman Ghorbanzade
*/
public class File extends FSElement {
  /**
  *
  */
  public File(String name, String owner, Date created, int size) {
    super(name, owner, created, size);
  }
}
