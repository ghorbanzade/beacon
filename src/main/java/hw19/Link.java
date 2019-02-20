//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;

/**
 * This class defines a link as a file system element that points to
 * another file system element.
 *
 * @author Pejman Ghorbanzade
 * @see FileSystemElement
 * @see File
 */
public final class Link extends FileSystemElement {

  /**
   * A link is a simple pointer to another file system element.
   */
  private final FileSystemElement target;

  /**
   * To create a link, its name, its owner and the element it should
   * point to must be specified.
   *
   * @param name name of the link
   * @param owner owner of the link
   * @param target the element the pointer points to
   */
  public Link(String name, String owner, FileSystemElement target) {
    super(FileSystemElementType.LINK, name, owner);
    this.target = target;
  }

  /**
   * Size of a link is size of the element it points to.
   *
   * @return size of the element the link points to.
   */
  public int getSize() {
    return this.target.getSize();
  }

  /**
   * This method simply gives access to the element the link is pointing to.
   *
   * @return the element the link points to
   */
  public FileSystemElement getTarget() {
    if (this.target instanceof Link) {
      return ((Link) this.target).getTarget();
    } else {
      return this.target;
    }
  }

}
