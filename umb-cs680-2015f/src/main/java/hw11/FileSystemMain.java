//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw11;

import java.util.Date;

/**
* This program aims to develop a program that allows handling a simplified
* version of filesystem access operations. Using a mixture of singleton and
* composite design patterns, the program first initializes the filesystem
* according to the directory structure specified in the problem (available in
* accompanied document) and then prints the size of the root and a verbose
* listing of all the elements within the filesystem.
*
* <p>Although our design allows for having different owners for different
* filesystem elements, for simplicy, we assume the owner of all filesystem
* elements is initaly a hard coded user. To maintain the rule of generality
* each filesystem element is provided a mutator method to specify a new
* owner.
*
* @author Pejman Ghorbanzade
* @see FileSystem
*/
public final class FileSystemMain {
  public static final String user = "pejman";

  /**
  * Main method of the program that first initalizes the file system
  * and then prints the size of the root and a verbose presentation of
  * all the elements within the filesystem.
  *
  * @param args command line arguments given to this program.
  */
  public static void main(String[] args) {
    FileSystem fs = initFileSystem();
    fs.showAllElements();
    //
    NameSearchVisitor visitor1 = new NameSearchVisitor("t");
    fs.getRoot().accept(visitor1);
    System.out.printf("Files that contain the phrase \"t\": %s%n",
                      visitor1.getResult());
    // disk size of root directory
    DiskSizeVisitor visitor2 = new DiskSizeVisitor();
    fs.getRoot().accept(visitor2);
    System.out.printf("Total disk size of filesystem: %d%n",
                      visitor2.getSize());
    // number of files in root directory
    ElementCountVisitor visitor3 = new ElementCountVisitor();
    fs.getRoot().accept(visitor3);
    System.out.printf("Total number of files in filesystem: %d%n",
                      visitor3.getCount(FileSystemElementType.FILE));
  }

  /**
  * This method helps avoid having a chunky main method by initalizing the
  * filesystem's single instance according to directory structure given
  * in the problem.
  *
  * @return an initialized singleton filesystem
  */
  public static FileSystem initFileSystem() {
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
    FileSystem fs = FileSystem.getFileSystem();
    fs.getRoot().appendChild(dir2);
    fs.getRoot().appendChild(dir3);
    return fs;
  }

  /**
  * This class must not be instantiated.
  */
  private FileSystemMain() {
  }
}
