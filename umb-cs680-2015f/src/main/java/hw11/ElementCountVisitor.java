//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw11;

import java.util.HashMap;

/**
* This visitor class provides a tool to count number of elements of a specific
* type within a given directory.
*
* @author Pejman Ghorbanzade
* @see FileSystemVisitor
*/
public final class ElementCountVisitor implements FileSystemVisitor {
  /**
  * a hashmap to hold counters for different file system element types.
  */
  private final HashMap<FileSystemElementType, Integer> counts =
      new HashMap<FileSystemElementType, Integer>();

  /**
  * This constructor initializes all counters for file, directory and link
  * to zero.
  */
  public ElementCountVisitor() {
    this.counts.put(FileSystemElementType.FILE, 0);
    this.counts.put(FileSystemElementType.DIRECTORY, 0);
    this.counts.put(FileSystemElementType.LINK, 0);
  }

  /**
  * This method will run the algorithm on the file that is being visited.
  *
  * @param file the file on which the algorithm is being executed.
  */
  public void visit(File file) {
    increment(FileSystemElementType.FILE);
  }

  /**
  * This method will run the algorithm on the directory that is being visited.
  *
  * @param directory the directory on which the algorithm is being executed.
  */
  public void visit(Directory directory) {
    increment(FileSystemElementType.DIRECTORY);
  }

  /**
  * This method will run the algorithm on the link that is being visited.
  *
  * @param link the link on which the algorithm is being executed.
  */
  public void visit(Link link) {
    increment(FileSystemElementType.LINK);
  }

  /**
  * This method will increment the counter corresponding to type of the element
  * under investigation.
  *
  * @param type the type of the element that is being visited
  */
  public void increment(FileSystemElementType type) {
    this.counts.put(type, counts.get(type) + 1);
  }

  /**
  * This method will return the number of available file system elements
  * of a given type.
  *
  * @param type the file type whose count is requested
  * @return the number of available elements of the specified type
  */
  public int getCount(FileSystemElementType type) {
    return this.counts.get(type);
  }
}
