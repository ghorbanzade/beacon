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
public final class Directory extends FSElement {

  /**
   *
   */
  private final ArrayList<FSElement> children = new ArrayList<FSElement>();

  /**
   *
   */
  public Directory(String name, String owner) {
    super(FSElementType.DIRECTORY, name, owner);
  }

  /**
   *
   */
  public void appendChild(FSElement element) {
    element.setParent(this);
    this.children.add(element);
  }

  /**
   *
   */
  public ArrayList<FSElement> getChildren() {
    return this.children;
  }

  /**
   *
   */
  public int getSize() {
    int size = 0;
    for (FSElement element: this.getChildren()) {
      if (element instanceof Link == false) {
        size += element.getSize();
      }
    }
    return size;
  }

  /**
   *
   */
  public void showAllElements() {
    System.out.printf("%s:%n", this.getFullPath());
    System.out.printf("total %d%n", this.getSize());
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
