//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.Comparator;

/**
* This class provides a method to compare file system elements
* based on the time they are last modified. This method may be
* used to list file system elements of a given directory.
*
* @author Pejman Ghorbanzade
* @see FileSystemElementComparator
*/
public class TimeComparator extends FileSystemElementComparator {
  /**
  * This method overrides the pre-defined compare method to compare
  * two file system elements based on the date they are last modified.
  *
  * @param element1 the file system element we are comparing against
  * @param element2 the file system element we are comparing with
  * @return a negative integer in case the first element was modified
  *         more recently.
  */
  @Override
  public int compare(FileSystemElement element1,
                     FileSystemElement element2) {
    return element1.getLastModified().compareTo(element2.getLastModified());
  }
}
