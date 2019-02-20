//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class defines the object that is to be passed to the thread object.
 *
 * @author Pejman Ghorbanzade
 * @see Interrupter
 */
public class ThreadSafeFlag {

  /**
   * Each object of this class has a threading lock, an interrupter object
   * that defines how the thread should run and a flag to prevent
   * access of multiple threads to shared objects.
   */
  private boolean done = false;
  private final ReentrantLock lock;
  private final Interrupter interrupter;

  /**
   * The constructor simply initializes the lock and the interrupter
   * object which implements the runnable class.
   */
  public ThreadSafeFlag() {
    this.lock = new ReentrantLock();
    this.interrupter = new Interrupter(this);
  }

  /**
   * This method prints '#' character as long as the flag is set to false.
   *
   * <p>To ensure thread safety of this method, balking is used to
   * skip the execution if the flag is already set.
   */
  public void work() {
    while (true) {
      this.lock.lock();
      try {
        if (!this.done) {
          break;
        }
        System.out.printf("#%n");
      } finally {
        this.lock.unlock();
      }
    }
    System.out.printf("Flag state changed.%n");
  }

  /**
   * This getter method allows access to the interrupter attribute
   * of this object.
   *
   * @return the interrupter object that implements the runnable interface.
   */
  public Interrupter getInterrupter() {
    return this.interrupter;
  }

  /**
   * Sets the flag to indicate a thread has previously executed
   * the run logic for interrupter object.
   *
   * <p>This method is thread safe in that only the thread which holds
   * the lock can set the flag.
   */
  public void setDone() {
    this.lock.lock();
    try {
      this.done = true;
    } finally {
      this.lock.unlock();
    }
  }

}
