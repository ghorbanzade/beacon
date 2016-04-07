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
 *
 */
public abstract class FSElement {

  /**
   *
   */
  private String name;
  private String owner;
  private final Date created;
  private Date lastModified;
  private Directory parent = null;
  private final FSElementType type;

  /**
   *
   */
  public FSElement(FSElementType type, String name, String owner) {
    this.type = type;
    this.name = name;
    this.owner = owner;
    this.created = new Date();
    this.lastModified = new Date();
  }

  /**
   *
   */
  public boolean isLeaf() {
    return (this instanceof File || this instanceof Link || ((Directory) this).getChildren().isEmpty());
  }

  /**
   *
   */
  public String getName() {
    return this.name;
  }

  /**
   *
   */
  public String getOwner() {
    return this.owner;
  }

  /**
   *
   */
  public Date getCreated() {
    return (Date) this.created.clone();
  }

  /**
   *
   */
  public Date getLastModified() {
    return (Date) this.lastModified.clone();
  }

  /**
   *
   */
  public abstract int getSize();

  /**
   *
   */
  public Directory getParent() {
    return this.parent;
  }

  /**
   *
   */
  public FSElementType getType() {
    return this.type;
  }

  /**
   *
   */
  public void setName(String name) {
    this.name = name;
    this.updateLastModified();
  }

  /**
   *
   */
  public void setOwner(String owner) {
    this.owner = owner;
    this.updateLastModified();
  }

  /**
   *
   */
  protected void updateLastModified() {
    this.lastModified = new Date();
    if (this.getParent() != null) {
      this.getParent().updateLastModified();
    }
  }

  /**
   *
   */
  public void setParent(Directory parent) {
    this.parent = parent;
    this.updateLastModified();
  }

  /**
   *
   */
  public String getFullPath() {
    StringBuilder path = new StringBuilder(this.getName());
    FSElement dir = this;
    while (dir.getParent() != null) {
      dir = dir.getParent();
      path.insert(0, dir.getName() + "/");
    }
    return path.toString();
  }

  /**
   *
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
