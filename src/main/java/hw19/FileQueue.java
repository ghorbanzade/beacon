//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;

/**
 *
 */
public final class FileQueue {

  /**
   *
   */
  private final int threshold;
  private final ArrayList<File> files;

  /**
   *
   */
  public FileQueue(int threshold) {
    this.threshold = threshold;
    this.files = new ArrayList<File>();
  }

  /**
   *
   */
  public void put(File file) {
    this.files.add(file);
  }

  /**
   *
   */
  public File get() {
    return (this.files.isEmpty()) ? null : this.files.remove(0);
  }

  /**
   *
   */
  public boolean isFull() {
    return this.threshold <= this.files.size();
  }

}
