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
public class Directory extends FSElement {
  private ArrayList<FSElement> children = new ArrayList<FSElement>();

  /**
  *
  *
  * @param name
  * @param owner
  * @param created
  */
  public Directory(String name, String owner, Date created) {
    super(name, owner, created, 0);
  }

  /**
  *
  *
  * @param element
  */
  public void appendChild(FSElement element) {
    element.setParent(this);
    this.children.add(element);
  }

  /**
  *
  *
  * @return a list of subdirectories and immediate files in current directory
  */
  public ArrayList<FSElement> getChildren() {
    return this.children;
  }

  /**
  *
  *
  * @return the full path
  */
  public String getFullPath() {
    StringBuilder path = new StringBuilder(this.getName());
    Directory dir = this;
    while (dir.getParent() != null) {
      dir = dir.getParent();
      path.insert(0, dir.getName() + "/");
    }
    return path.toString();
  }

  /**
  *
  */
  public void showAllElements() {
    System.out.printf("%s:\n", this.getFullPath());
    System.out.printf("total %d\n", this.getSize());
    for (FSElement element: this.getChildren()) {
      System.out.println(element);
    }
    System.out.println();
    for (FSElement element: this.getChildren()) {
      if (element instanceof Directory) {
        ((Directory) element).showAllElements();
      }
    }
  }
}
