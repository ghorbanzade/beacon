//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.List;
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
  public FileSystemElement getElementByFullPath(List<String> path)
      throws UnsupportedOperationException {
    FileSystemElement target = this.rootDir;
    Directory directory = this.rootDir;
    for (int i = 0; i < path.size(); i++) {
      FileSystemElement child = directory.getChild(path.get(i));
      if (child == null) {
        String message = String.format("element %s not found", path.get(i));
        throw new UnsupportedOperationException(message);
      } else if (i < path.size() - 1) {
        if (child instanceof Directory) {
          directory = (Directory) child;
        } else {
          String message = String.format("%s not a directory", child.getName());
          throw new UnsupportedOperationException(message);
        }
      } else {
        target = child;
      }
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
