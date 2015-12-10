//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
* This class is a super class of Directory and File and is defined as abstract.
* A file system element can be added to the file system instance.
*
* @author Pejman Ghorbanzade
* @see FileSystemElementType
* @see Directory
* @see File
*/
public abstract class FileSystemElement {
  /**
  * Each file system element has a type, name, an owner, date of creation
  * and a date that the element is last modified. In addition, a file system
  * elemenet may have a parent which is set once the element is added to the
  * filesystem instance.
  */
  private final FileSystemElementType type;
  private String name;
  private String owner;
  private final Date created;
  private Date lastModified;
  private Directory parent = null;

  /**
  * Constructor of the filesystem element. Upon creation, the date the element
  * is last modified is set to the creation date. Noteworthy that instantiation
  * does not set parent of the file system element. Parent is set when the
  * filesystem element is added to the filesystem via appendChild() method of
  * a directory object.
  *
  * @param type one of enum values defined in FileSystemElementType class
  * @param name name of the file system element
  * @param owner name of the user who created the file system element
  */
  public FileSystemElement(FileSystemElementType type, String name, String owner) {
    this.type = type;
    this.name = name;
    this.owner = owner;
    this.created = new Date();
    this.lastModified = new Date();
  }

  /**
  * This method determines if the current file system element is a leaf
  * or not. In case the element is a File or Link, it is definitely a leaf.
  * In case the element is a Directory, it is a leaf if it has no child.
  *
  * @return true if the object is an instance of file or link
  *         or if the object is a directory with no children.
  */
  public boolean isLeaf() {
    return (this instanceof File
          || this instanceof Link
          || ((Directory) this).getChildren().isEmpty()
          );
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
    return (Date) this.created.clone();
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
    return (Date) this.lastModified.clone();
  }

  /**
  * This method gives access to the size of the current file system element.
  * If the element is a directory, this method recursively calculates the size
  * of all its children.
  *
  * @return the size of the file system element including the size of its
  *         children.
  */
  public abstract int getSize();

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
    if (this.getParent() != null) {
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
  * This method find the full directory path resulting from a walk from root
  * to the current node.
  *
  * @return the full path of current file system element, starting from root
  */
  public String getFullPath() {
    StringBuilder sb = new StringBuilder();
    FileSystemElement dir = this;
    while (dir.getParent() != null) {
      sb.insert(0, "/" + dir.getName());
      dir = dir.getParent();
    }
    return (sb.length() == 0) ? "/" : sb.toString();
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
    SimpleDateFormat df = new SimpleDateFormat("MMM dd kk:mm", Locale.US);
    StringBuilder entry = new StringBuilder();
    entry.append(String.format("%-10s %-8s %4d %12s %s",
        this.type.getName(), this.owner, this.getSize(),
        df.format(this.lastModified), this.name));
    if (this instanceof Link) {
      String name = ((Link) this).getTarget().getFullPath();
      entry.append(String.format(" --> %s", name));
    }
    return entry.toString();
  }
}
