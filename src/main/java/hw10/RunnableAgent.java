//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see AutoSaver
 * @see Editor
 */
public abstract class RunnableAgent implements Runnable {

  /**
   *
   */ 
  private File file;
  private String name = "autosaver";

  /**
   * 
   *
   * @param file
   */
  public RunnableAgent(File file) {
    this.file = file;
  }

  /**
   *
   */
  public abstract void run();

  /**
   *
   *
   * @return
   */
  public File getFile() {
    return this.file;
  }

}
