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
 * @see RunnableAgent
 * @see File
 */
public class AutoSaver extends RunnableAgent {

  /**
   *
   */ 
  private String name = "autosaver";

  /**
   * 
   *
   * @param file
   */
  public AutoSaver(File file) {
    super(file);
  }

  /**
   *
   */
  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      this.getFile().save(this.name);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
