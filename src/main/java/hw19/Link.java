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
public final class Link extends FSElement {

  /**
   *
   */
  private final FSElement target;

  /**
   *
   */
  public Link(String name, String owner, FSElement target) {
    super(FSElementType.LINK, name, owner);
    this.target = target;
  }

  /**
   *
   */
  public int getSize() {
    return this.target.getSize();
  }

  /**
   *
   */
  public FSElement getTarget() {
    if (this.target instanceof Link) {
      return ((Link) this.target).getTarget();
    } else {
      return this.target;
    }
  }

}
