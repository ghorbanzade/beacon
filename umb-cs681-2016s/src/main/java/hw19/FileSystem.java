//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

/**
 * This class defines a file system as an object with a root directory.
 *
 * @author Pejman Ghorbanzade
 * @see Directory
 * @see File
 * @see Link
 */
public final class FileSystem {

  /**
   * A file system keeps track of nothing other than what its root
   * directory is.
   */
  private final Directory root = new Directory("root", "root");

  /**
   * Once a file system is constructed, it is loaded by a number of file
   * system elements based on the program configuration file.
   *
   * @param cfg config reader object to load configuratoin parameters from
   */
  public FileSystem(ConfigReader cfg) {
    //int fsNum = Integer.parseInt(cfg.get("elements.number"));
    //FileNameReader fnr = new FileNameReader(cfg.get("filename.list"));
    this.load();
  }

  /**
   * This method allows user to see the directory structure of the file
   * system.
   */
  public void show() {
    this.root.showAllElements();
  }

  /**
   * This method loads the file system with a set of file system elements.
   * Ideally, the file system should be randomly generated based on the
   * parameters in the configuration file but at the moment, this process
   * is only manual.
   */
  private void load() {
    String user = "pejman";
    Directory dir1 = new Directory("pictures", user);
    File file1 = new File("e", user, 8);
    dir1.appendChild(file1);
    dir1.appendChild(new File("f", user, 28));
    dir1.appendChild(new Link("y", user, file1));
    Directory dir2 = new Directory("system", user);
    dir2.appendChild(new File("a", user, 256));
    dir2.appendChild(new File("b", user, 12));
    dir2.appendChild(new File("c", user, 64));
    Directory dir3 = new Directory("home", user);
    dir3.appendChild(new File("d", user, 56));
    dir3.appendChild(new Link("x", user, dir2));
    dir3.appendChild(dir1);
    this.root.appendChild(dir2);
    this.root.appendChild(dir3);
  }

  /**
   * This method allows access to the root of the file system and is used
   * both for the main method and for crawlers.
   *
   * @return the root of the file system
   */
  public Directory getRoot() {
    return this.root;
  }

}
