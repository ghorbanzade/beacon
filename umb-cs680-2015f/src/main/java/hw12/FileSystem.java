//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.List;

/**
* This singleton class defines the file system that hosts all the
* file system elements.
*
* @author Pejman Ghorbanzade
* @see FileSystemElement
* @see Cli
*/
public final class FileSystem {
  /**
  * Each file system instance will have a root directory that
  * is initialized upon instantiation.
  */
  private static final String INITUSER = "root";
  private static FileSystem instance = null;
  private Directory rootDir = null;

  /**
  * There is only one instance of File System during the runtime
  * of the program. Defining constructor as private will ensure
  * this class will not be directly instantiated.
  */
  private FileSystem() {
    this.rootDir = new Directory("root", INITUSER);
    this.rootDir.appendChild(new Directory("home", INITUSER));
  }

  /**
  * This static method is used to access the one and only instance
  * of the FileSystem class.
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
  * This method takes a path list of file system element names and
  * returns the file system element the path is pointing to.
  *
  * @param path a list of file system element names beginning from
  *        the root directory to the target file system element
  * @return the file system element located in the given path list
  * @throws UnsupportedOperationException in case the path list is
  *         inconsistent with file system directory structure
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
