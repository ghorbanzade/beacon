//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw11;

import java.util.ArrayList;
import java.util.Date;

/**
* This class defines the link as a filesystem element that points to another
* file system element.
*
* @author     Pejman Ghorbanzade
* @see        FileSystemElement
* @see        Directory
*/
public final class Link extends FileSystemElement {
  /**
  * Each link has a target that it points to.
  */
  private final FileSystemElement target;

  /**
  * Construcor for the link class.
  *
  * @param name name of the link
  * @param owner owner of the link
  * @param target target of the link
  */
  public Link(String name, String owner, FileSystemElement target) {
    super(FileSystemElementType.LINK, name, owner);
    this.target = target;
  }

  /**
  * This method implements getSize() method of FileSystemElement object by simply
  * returning the size of the target. Since a link cannot be its own target
  * we are sure the infinite loop is avoided.
  *
  * @return size of the target
  */
  public int getSize() {
    return this.target.getSize();
  }

  /**
  * This method will take any subclass of the FileSystemVisitor class and
  * executes its algorithm on the current link.
  *
  * @param visitor an object from a subclass of FileSystemVisitor class.
  */
  public void accept(FileSystemVisitor visitor) {
    visitor.visit(this);
  }

  /**
  * This method will give access to the file system element that is set as the
  * target. In case the target itself is a link, the method will recursively
  * follow the target such that the returned file system element is never a
  * link.
  *
  * @return the file system element that the link is pointing to
  */
  public FileSystemElement getTarget() {
    return (this.target instanceof Link) ? ((Link) this.target).getTarget() : this.target;
  }
}
