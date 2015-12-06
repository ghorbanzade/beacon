//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw11;

import java.util.ArrayList;

/**
* This visitor class defines the policy to compute the total disk size of
* a given file system.
*
* @author Pejman Ghorbanzade
* @see FileSystemVisitor
*/
public final class DiskSizeVisitor implements FileSystemVisitor {
  /**
  * Each disk size visitor has an attribute that specifies the total size of
  * all the elements it is an acesstor of of.
  */
  private int size = 0;

  /**
  * Since the only attribute of a disk size visitor object is its size,
  * the constructor for this class is an empty one.
  */
  public DiskSizeVisitor() {
  }

  /**
  * The disk size of a file system will increase once a file system element
  * of type File is visited within that file system.
  *
  * @param file the file on which the policy should be applied.
  */
  public void visit(File file) {
    this.size += file.getSize();
  }

  /**
  * The disk size of a file system is only affected by the size of the
  * files within that file system and since we assume a directory has no
  * size itself, the visit method on a directory performs no action.
  *
  * <b>Note:</b> Visiting a directory will automatically lead to visiting
  * all its children.
  *
  * @param directory the directory on which the policy should be applied.
  */
  public void visit(Directory directory) {
  }

  /**
  * The disk size of a file system is only affected by the size of the files
  * within that file system and since we assume a link has no size itself,
  * the visit method on a link performs no action.
  *
  * @param link the link on which the policy should be applied
  */
  public void visit(Link link) {
  }

  /**
  * This method will return the total size of the current file system element
  * as well as the size of all its predecessors.
  *
  * @return the total size of the current file system element including all
  *         its predecessors.
  */
  public int getSize() {
    return this.size;
  }
}
