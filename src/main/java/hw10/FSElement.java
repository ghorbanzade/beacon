//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw10;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
* This class is a super class of Directory and File and is defined as abstract.
* A file system element can be added to the file system instance.
*
* @author     Pejman Ghorbanzade
* @see        Directory
* @see        File
*/
public abstract class FSElement {
  /**
  * Each file system element has a name, an owner, a size, date of creation
  * and a date that the element is last modified. In addition, a file system
  * elemenet may have a parent which is set once the element is added to the
  * filesystem instance.
  */
  private String name;
  private String owner;
  private final Date created;
  private Date lastModified;
  private int size;
  private Directory parent = null;

  /**
  * Constructor of the filesystem element. Upon creation, the date the element
  * is last modified is set to the creation date. Noteworthy that instantiation
  * does not set parent of the file system element. Parent is set when the
  * filesystem element is added to the filesystem via appendChild() method of
  * a directory object.
  *
  * @param name name of the file system element
  * @param owner name of the user who created the file system element
  * @param created the date at which the file system element is created
  * @param size size of the file system element. If the file system element is
  *             a drectory, the size is zero.
  */
  public FSElement(String name, String owner, Date created, int size) {
    this.name = name;
    this.owner = owner;
    this.created = created;
    this.lastModified = created;
    this.size = size;
  }

  /**
  * A method which determines if the current file system element is a leaf
  * or not. In case the element is a File, it is definitely a leaf. In case
  * the element is a Directory, it is a leaf if it has no child.
  *
  * @return true if the object is an instance of file or if the object is a
  *               directory with no children.
  */
  public boolean isLeaf() {
    return (this instanceof File || ((Directory) this).getChildren().isEmpty());
  }

  /**
  * This accessor method is used to get the name of the file system element.
  *
  * @return the name of the current file system element
  */
  public String getName() {
    return this.name;
  }

  /**
  * This accessor method is used to get the name of the user that owns the
  * file system element.
  *
  * @return the name of the user that owns the file system element
  */
  public String getOwner() {
    return this.owner;
  }

  /**
  * This method allows access to the date at which the file system
  * element is created. This method is not being used in other methods and
  * is developed for extensibility and possible future use.
  *
  * @return the date at which the file system element is created.
  */
  public Date getCreated() {
    return this.created;
  }

  /**
  * This method allows access to the date at which the current file system
  * element or any element under them is updated. The last modified date
  * is updated for a directory if one of its children is updated.
  *
  * @return the date at which the file system element or any of its children
  *         is updated.
  */
  public Date getLastModified() {
    return this.lastModified;
  }

  /**
  * This method gives access to the size of the current file system element.
  * If the element is a directory, this method recursively calculates the size
  * of all its children.
  *
  * @return the size of the file system element including the size of its
  *         children.
  */
  public int getSize() {
    if (this instanceof File) {
      return this.size;
    } else {
      int size = 0;
      for (FSElement element: ((Directory) this).getChildren()) {
        size += element.getSize();
      }
      return size;
    }
  }

  /**
  * This method gets the parent directory of the current file system element.
  * This method will return null in case it is called in root directory of the
  * file system.
  *
  * @return the parent directory of current file system element
  */
  public Directory getParent() {
    return this.parent;
  }

  /**
  * This method allows for renaming a file system element. This operation
  * updates the last modified date for current element and all its ancestors.
  * This method is not used and it only provided for extensibility.
  *
  * @param name the new name for current system element
  */
  public void setName(String name) {
    this.name = name;
    this.updateLastModified();
  }

  /**
  * This method allows updating the owner of a file system element.
  * In case we had a notion of user privileges, this method must have checked
  * if the user performing this operation has the higher access level than the
  * previous owner of the file system element. This operation updates the last
  * modified date for current element and all its ancestors.
  * This method is not used and it only provided for extensibility.
  *
  * @param owner the new owner of this file system element
  */
  public void setOwner(String owner) {
    this.owner = owner;
    this.updateLastModified();
  }

  /**
  * This method recursively updates the last modified date for current file
  * system element and all of its ancestors.
  */
  protected void updateLastModified() {
    this.lastModified = new Date();
    while (this.getParent() != null) {
      this.getParent().updateLastModified();
    }
  }

  /**
  * This method allows for moving a file system element to another directory.
  * This operation will entail an update on last modified date to the current
  * element and all its new ancestors.
  *
  * @param parent the new directory to which the element is moving.
  */
  public void setParent(Directory parent) {
    this.parent = parent;
    this.updateLastModified();
  }

  /**
  * This method defines how a file system element will be printed on the
  * console. This method is used to show all elements of a directory or the
  * file system instance.
  *
  * @return a string literal containing information about current file system
  *         element.
  */
  @Override
  public String toString() {
    String typeIndicator = (this instanceof File) ? "file" : "directory";
    SimpleDateFormat df = new SimpleDateFormat("MMM dd kk:mm");
    StringBuilder entry = new StringBuilder();
    entry.append(String.format("%-10s %-8s %4d %12s %s",
        typeIndicator, this.owner, this.getSize(),
        df.format(this.lastModified), this.name));
    return entry.toString();
  }
}
