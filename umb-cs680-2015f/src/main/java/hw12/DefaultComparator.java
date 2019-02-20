//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.Comparator;

/**
* This class defines the default method for comparing two file
* system elements.
*
* @author   Pejman Ghorbanzade
* @see FileSystemElementComparator
* @see FileSystemElementType
*/
public class DefaultComparator extends FileSystemElementComparator {
  /**
  * This method provides the default way to compare two file system
  * elements first based on their type and name. Based on this
  * method, directories will be listed first, then file and then
  * links. If two file system elements are of the same type they
  * will be compared alphabetically.
  *
  * @param element1 the file system element we are comparing against
  * @param element2 the file system element we are comparing with
  * @return the file system element that should be listed first
  */
  @Override
  public int compare(FileSystemElement element1,
                     FileSystemElement element2) {
    int comparison = element1.getType().getName()
                             .compareTo(element2.getType().getName());
    return (comparison == 0)
          ? element1.getName().compareTo(element2.getName())
          : comparison;
  }
}
