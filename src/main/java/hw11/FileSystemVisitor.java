//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw11;

/**
* This class defines a visitor as any class that defines how to execute an
* algorithm on different file system element types.
*
* @author Pejman Ghorbanzade
* @see FileSystemElement
*/
public interface FileSystemVisitor {
  /**
  * Each visitor must define how its policy is applied to file objects.
  *
  * @param file the file on which the visitor algorithm should be executed.
  */
  public void visit(File file);

  /**
  * Each visitor must define how its policy is applied to link objects.
  *
  * @param link the link on which the visitor algorithm should be executed.
  */
  public void visit(Link link);

  /**
  * Each visitor must define how its policy is applied to link objects.
  *
  * @param directory the directory on which the visitor algorithm should be
  *                  executed.
  */
  public void visit(Directory directory);
}
