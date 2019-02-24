//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.io.Serializable;
import java.util.Comparator;

/**
* This abstract class structures the set of methods to compare
* file system elements. These methods may be used in List Command
* where file system elements of a specific directory are listed
* in a pre-defined order.
*
* @author Pejman Ghorbanzade
* @see ListCommand
*/
public abstract class FileSystemElementComparator
      implements Comparator<FileSystemElement>, Serializable {

  /**
  * Any subclass of this class must propose the way to compare
  * to file system elements.
  *
  * @param element1 the file system element we are comparing against
  * @param element2 the file system element we are comparing with
  * @return negative in case the first file system element should be
  *         listed before the second one.
  */
  @Override
  public abstract int compare(FileSystemElement element1,
      FileSystemElement element2);
}
