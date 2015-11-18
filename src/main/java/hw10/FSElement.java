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
*
*
* @author     Pejman Ghorbanzade
*/
public abstract class FSElement {

  private String name;
  private String owner;
  private Date created;
  private Date lastModified;
  private int size;
  private Directory parent = null;

  /**
  *
  */
  public FSElement(String name, String owner, Date created, int size) {
    this.name = name;
    this.owner = owner;
    this.created = created;
    this.lastModified = created;
    this.size = size;
  }

  /**
  *
  *
  * @return true if the object is an instance of file or if the object is a
  *               directory with no children.
  */
  public boolean isLeaf() {
    if (this instanceof File || ((Directory) this).getChildren().isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  /**
  *
  *
  * @return
  */
  public String getName() {
    return this.name;
  }

  /**
  *
  *
  * @return
  */
  public String getOwner() {
    return this.owner;
  }

  /**
  *
  *
  * @return
  */
  public Date getCreated() {
    return this.created;
  }

  /**
  *
  *
  * @return
  */
  public Date getLastModified() {
    return this.lastModified;
  }

  /**
  *
  *
  * @return
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
  *
  *
  * @return
  */
  public Directory getParent() {
    return this.parent;
  }

  /**
  *
  *
  * @param
  */
  public void setName(String name) {
    this.name = name;
  }

  /**
  *
  *
  * @param owner
  */
  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
  *
  */
  public void updateLastModified() {
    this.lastModified = new Date();
  }

  /**
  *
  *
  * @param parent
  */
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
  *
  *
  * @return a string literal containing information about current file system
  *         element
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
