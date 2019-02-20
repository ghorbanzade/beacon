//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * This abstract class defines a file system element as either a file or a
 * container of file or a pointer to file.
 *
 * @author Pejman Ghorbanzade
 * @see FileSystemElementType
 * @see Directory
 * @see File
 * @see Link
 */
public abstract class FileSystemElement {

  /**
   * Each file system element has a name, an owner, a date indicating when
   * it is constructed, a date indicated when it is last changed, its parent
   * and its type.
   */
  private String name;
  private String owner;
  private final Date created;
  private Date lastModified;
  private Directory parent = null;
  private final FileSystemElementType type;

  /**
   * To create an element, its type, name and owner should be specified.
   * The creation and modification dates are set the the current time
   * automatically.
   *
   * @param type type of the element
   * @param name name of the element
   * @param owner owner of the element
   */
  public FileSystemElement(
      FileSystemElementType type, String name, String owner) {
    this.type = type;
    this.name = name;
    this.owner = owner;
    this.created = new Date();
    this.lastModified = new Date();
  }

  /**
   * This method is used to check if the element has any children or not.
   * It is very useful and is used as termination condition for recursive
   * calls to crawler or printer methods.
   *
   * @return whether the element has any children
   */
  public boolean isLeaf() {
    return (this instanceof File
        || this instanceof Link
        || ((Directory) this).getChildren().isEmpty());
  }

  /**
   * This method gives the name of an element.
   *
   * @return the name of the element
   */
  public String getName() {
    return this.name;
  }

  /**
   * This simple method gives the user who owns this element. It is used to
   * print information about the element even in other classes.
   *
   * @return the name of the user that owns this element
   */
  public String getOwner() {
    return this.owner;
  }

  /**
   * The datetime a file system element is immutable and will not change
   * by any means.
   *
   * @return The date at which the element is constructed
   */
  public Date getCreated() {
    return (Date) this.created.clone();
  }

  /**
   * This method gives access to the last time an element or one of its
   * children has been modified.
   *
   * @return the date the element is last modified
   */
  public Date getLastModified() {
    return (Date) this.lastModified.clone();
  }

  /**
   * This abstract method gives size of the element as defined in classes
   * that define a type for file system element.
   *
   * @return size of the element as defined based on its type
   */
  public abstract int getSize();

  /**
   * This method gets the parent directory of the current element or null
   * of the element is the root of the file system.
   *
   * @return the parent directory of this element
   */
  public Directory getParent() {
    return this.parent;
  }

  /**
   * This accessor method returns the type of the file system element as
   * an enumeration item.
   *
   * @return the type of this element as an enum value
   */
  public FileSystemElementType getType() {
    return this.type;
  }

  /**
   * This method allows renaming a file system element. It is not used at
   * the moment by any class in this design.
   *
   * @param name the new name of the element
   */
  public void setName(String name) {
    this.name = name;
    this.updateLastModified();
  }

  /**
   * This method allows updating the owner of the element. It is not used
   * at the moment by any class in this design.
   *
   * @param owner the new owner of the element
   */
  public void setOwner(String owner) {
    this.owner = owner;
    this.updateLastModified();
  }

  /**
   * This method recursively updates the last modified date of current
   * file systm element and all its ancestors. Every change to the
   * element should follow by a call to this method.
   */
  protected void updateLastModified() {
    this.lastModified = new Date();
    if (this.getParent() != null) {
      this.getParent().updateLastModified();
    }
  }

  /**
   * This method allows placing a file system element under another
   * directory. This operation will entail an update on last modified
   * date to the moved element and all its ancestors.
   *
   * @param parent the new directory to which the element is moving
   */
  public void setParent(Directory parent) {
    this.parent = parent;
    this.updateLastModified();
  }

  /**
   * This method finds the full directory path resulting from a walk from
   * root to the current node.
   *
   * @return the full path of current element starting from the root
   */
  public String getFullPath() {
    StringBuilder path = new StringBuilder(this.getName());
    FileSystemElement dir = this;
    while (dir.getParent() != null) {
      dir = dir.getParent();
      path.insert(0, dir.getName() + "/");
    }
    return path.toString();
  }

  /**
   * This method defines how a file system element should be printed on
   * the console.
   *
   * <p>At the moment, every index action will call this method to print
   * info of the indexed element.
   *
   * @return a ready-to-print string containing info about this element
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
