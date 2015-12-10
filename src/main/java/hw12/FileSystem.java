//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.lang.UnsupportedOperationException;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class FileSystem {
  /**
  *
  */
  private static FileSystem instance = null;
  private final String INITIAL_USER = "root";
  private Directory rootDir = null;

  /**
  *
  */
  private FileSystem() {
    this.rootDir = new Directory("root", INITIAL_USER);
    this.rootDir.appendChild(new Directory("home", INITIAL_USER));
  }

  /**
  * This static method is used to access the one and only instance of the
  * FileSystem class.
  *
  * @return one and only one instance of the FileSystem
  */
  public static FileSystem getInstance() {
    if (instance == null) {
      instance = new FileSystem();
    }
    return instance;
  }

  /**
  *
  *
  * @param path
  * @return
  */
  public FileSystemElement getElementByFullPath(ArrayList<String> path) throws UnsupportedOperationException {
    System.out.println(path);
    Directory directory = this.rootDir;
    FileSystemElement target = this.rootDir;
    while (path.size() > 1) {
      FileSystemElement element = directory.getChild(path.remove(0));
      if (element instanceof Directory) {
        directory = (Directory) element;
      } else {
        String message = String.format("%s not a directory", element.getName());
        throw new UnsupportedOperationException(message);
      }
    }
    if (path.size() == 1) {
      target = directory.getChild(path.remove(0));
    }
    return target;
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
