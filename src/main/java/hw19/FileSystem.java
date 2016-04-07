//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

/**
 *
 */
public final class FileSystem {

  /**
   *
   */
  private static FileSystem instance = null;
  private Directory root = null;

  /**
   *
   */
  private FileSystem() {
    this.root = new Directory("root", "root");
  }

  /**
   *
   */
  public static FileSystem getFileSystem() {
    if (instance == null) {
      instance = new FileSystem();
    }
    return instance;
  }

  /**
   *
   */
  public void showAllElements() {
    this.root.showAllElements();
  }

  /**
   *
   */
  public Directory getRoot() {
    return this.root;
  }

}
