//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw10;

import java.util.Date;

/**
* This program aims to develop a program that allows handling a simplified
* version of filesystem access operations. Using a mixture of singleton and
* composite design patterns, the program first initializes the filesystem
* according to the directory structure specified in the problem (available in
* accompanied document) and then prints the size of the root and a verbose
* listing of all the elements within the filesystem.
*
* @author     Pejman Ghorbanzade
*/
public final class FileSystemMain {
  /**
  * Although our design allows for having different owners for different
  * filesystem elements, for simplicy, we assume the owner of all filesystem
  * elements is initaly a hard coded user. To maintain the rule of generality
  * each filesystem element is provided a mutator method to specify a new
  * owner.
  */
  public static final String user = "pejman";

  /**
  * Main method of the program that first initalizes the file system
  * and then prints the size of the root and a verbose presentation of
  * all the elements within the filesystem.
  */
  public static void main(String[] args) {
    FileSystem fs = initFileSystem();
    System.out.printf("size of root is: %d\n", fs.getRoot().getSize());
    fs.showAllElements();
  }

  /**
  * This method helps avoid having a chunky main method by initalizing the
  * filesystem's single instance according to directory structure given
  * in the problem.
  *
  * @return an initialized singleton filesystem
  */
  public static FileSystem initFileSystem() {
    FileSystem fs = FileSystem.getFileSystem();
    fs.getRoot().appendChild(new Directory("system", user, new Date()));
    fs.getRoot().appendChild(new Directory("home", user, new Date()));
    ((Directory) fs.getRoot().getChildren().get(0)).appendChild(new File("a", user, new Date(), 256));
    ((Directory) fs.getRoot().getChildren().get(0)).appendChild(new File("b", user, new Date(), 12));
    ((Directory) fs.getRoot().getChildren().get(0)).appendChild(new File("c", user, new Date(), 64));
    ((Directory) fs.getRoot().getChildren().get(1)).appendChild(new File("d", user, new Date(), 56));
    ((Directory) fs.getRoot().getChildren().get(1)).appendChild(new Directory("pictures", user, new Date()));
    ((Directory) ((Directory) fs.getRoot().getChildren().get(1)).getChildren().get(1)).appendChild(new File("e", user, new Date(), 8));
    ((Directory) ((Directory) fs.getRoot().getChildren().get(1)).getChildren().get(1)).appendChild(new File("f", user, new Date(), 28));
    return fs;
  }
}
