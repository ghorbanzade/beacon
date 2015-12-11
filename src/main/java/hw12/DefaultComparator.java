//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.Comparator;

/**
*
*
* @author   Pejman Ghorbanzade
*/
public class DefaultComparator extends FileSystemElementComparator {
  /**
  *
  *
  * @param element1 the file system element we are comparing against
  * @param element2 the file system element we are comparing with
  * @return
  */
  @Override
  public int compare(FileSystemElement element1,
                     FileSystemElement element2) {
    int comparison = element1.getType().compareTo(element2.getType());
    return (comparison == 0)
          ? element1.getName().compareTo(element2.getName())
          : comparison;
  }
}
