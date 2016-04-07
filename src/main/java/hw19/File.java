//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;

/**
 *
 */
public final class File extends FSElement {

  /**
   *
   */
  private final int size;

  /**
   *
   */
  public File(String name, String owner, int size) {
    super(FSElementType.FILE, name, owner);
    this.size = size;
  }

  /**
   *
   */
  public int getSize() {
    return this.size;
  }

}
