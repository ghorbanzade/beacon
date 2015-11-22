//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw10;

import java.util.Date;

/**
* This class presents the FileSystem and its root directory.
* Design of this class follows a classic singleton pattern which allows
* lazy instantiation of one single FileSystem object.
*
* @author     Pejman Ghorbanzade
* @see        FSElement
*/
public final class FileSystem {
  private static FileSystem instance = null;
  private Directory rootDir = null;

  /**
  * To disallow instantiation of our singleton class constructor is defined
  * as private. Once the signle instance is being construced, root directory
  * will be initialized.
  *
  * <p>The root directory will be named root and is owned by root user. It
  * doesn't have any parent directory and it's creation date is the time
  * at which initalization takes place.
  */
  private FileSystem() {
    this.rootDir = new Directory("root", "root", new Date());
  }

  /**
  * This static method is used to access the one and only instance of the
  * FileSystem class.
  *
  * @return one and only one instance of the FileSystem
  */
  public static FileSystem getFileSystem() {
    if (instance == null) {
      instance = new FileSystem();
    }
    return instance;
  }

  /**
  * This method will print the directory structure starting from the root
  * directory.
  */
  public void showAllElements() {
    this.rootDir.showAllElements();
  }

  /**
  * This method gives access to the root directory of the single instance of
  * the file system.
  *
  * @return the root directory of instance of the file system
  */
  public Directory getRoot() {
    return this.rootDir;
  }
}
