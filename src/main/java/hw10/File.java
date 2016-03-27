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
public class File {

  /**
   *
   */
  private String name;
  private int content = 0;
  private boolean changed = false;
  
  /**
   *
   *
   * @param name
   */
  public File(String name) {
    this.name = name;
  }

  /**
   *
   *
   * @param agent
   */
  public void change(String agent) {
    this.content++;
    this.changed = true;
  }

  /**
   *
   *
   * @param agent
   */
  public void save(String agent) {
    if (this.changed) {
      System.out.printf("saving %s revision %d by %s.%n",
          this.name, this.content, agent);
      this.changed = false;
    }
  }

}
