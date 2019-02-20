//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements the Runnable class to declare what a thread
 * should do if it is passed a ThreadSafeFlag object.
 *
 * @author Pejman Ghorbanzade
 * @see ThreadSafeFlag
 */
public class Interrupter implements Runnable {

  /**
   * An interrupter object simply takes count of to which ThreadSafeFlag
   * object it belongs. This information is used to set the flag for that
   * object once the run method is executed.
   */
  private final ThreadSafeFlag target;

  /**
   * This constructor takes the object to which it belongs and stores
   * it as an attribute.
   *
   * @param target the object that contains this interrupter object.
   */
  public Interrupter(ThreadSafeFlag target) {
    this.target = target;
  }

  /**
   * This method implements what a thread should do if a ThreadSafeFlag
   * object is passed to it.
   */
  public void run() {
    try {
      Thread.sleep(10);
      this.target.setDone();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
