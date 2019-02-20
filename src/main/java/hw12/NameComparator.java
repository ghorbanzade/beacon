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
* based on their name. This method may be used to list file
* system elements of a given directory.
*
* @author Pejman Ghorbanzade
* @see FileSystemElementComparator
*/
public class NameComparator extends FileSystemElementComparator {
  /**
  * This method overrides the pre-defined comparison method to
  * compare two file system elements based on their name.
  *
  * @param element1 the file system element we are comparing against
  * @param element2 the file system element we are comparing with
  * @return a negative integer in case first element should appear
  *         before the second element.
  */
  @Override
  public int compare(FileSystemElement element1,
                     FileSystemElement element2) {
    return element1.getName().compareTo(element2.getName());
  }
}
