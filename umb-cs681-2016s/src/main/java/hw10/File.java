//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class defines a file object that can be changed and saved either via
 * editors or an autosaver.
 *
 * @author Pejman Ghorbanzade
 * @see AutoSaver
 * @see Editor
 */
public class File {

  /**
   * A file has a name that is used for information purposes, a flag that
   * indicates if it changed or not, an attribute that shows the number of
   * revisions made so far and a lock that makes sure multiple threads do
   * not modify attributes independently at the same time.
   */
  private final String name;
  private int content = 0;
  private boolean changed = false;
  private final ReentrantLock lock;
  
  /**
   * It only requires to specify a name for a new file to be created.
   *
   * @param name name of the file to be created.
   */
  public File(String name) {
    this.name = name;
    this.lock = new ReentrantLock();
  }

  /**
   * This method allows the thread that holds the lock to change the file.
   *
   * @param agent the agent passed to a thread acting on this object
   */
  public void change(String agent) {
    this.lock.lock();
    try {
      if (this.changed) {
        return;
      }
      this.content++;
      this.changed = true;
    } finally {
      this.lock.unlock();
    }
  }

  /**
   * This method allows the thread that holds the lock to save the file
   * if it is unchanged.
   *
   * @param agent the agent passed to a thread acting on this object
   */
  public void save(String agent) {
    this.lock.lock();
    try {
      if (!this.changed) {
        System.out.printf("skipping save by %s%n", agent);
        return;
      }
      System.out.printf("saving %s revision %d by %s.%n",
          this.name, this.content, agent);
      this.changed = false;
    } finally {
      this.lock.unlock();
    }
  }

}
