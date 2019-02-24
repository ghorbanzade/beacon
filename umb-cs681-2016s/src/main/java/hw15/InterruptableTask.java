//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class defines an interruptable task that can be passed to a thread
 * for execution.
 *
 * @author Pejman Ghorbanzade
 */
public class InterruptableTask implements Runnable {

  /**
   * A task object has a lock that prevents simultaneous function calls by
   * different threads.
   */
  private final ReentrantLock lock;

  /**
   * The lock attribute will be initialized during instantiation.
   */
  public InterruptableTask() {
    this.lock = new ReentrantLock();
  }

  /**
   * A task object handed to a thread will continue to print true as long as
   * it is not interrupted by the main thread. When interrupted, the thread
   * will print false and dies.
   */
  public void run() {
    while (true) {
      try {
        this.lock.lock();
        if (Thread.interrupted()) {
          break;
        }
        System.out.println(true);
      } finally {
        this.lock.unlock();
      }
    }
    System.out.println(false);
  }

}
