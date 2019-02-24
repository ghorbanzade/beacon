//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class provides a thread-safe solution to the given InterruptableTask2
 * problem.
 *
 * @author Pejman Ghorbanzade
 * @see InterruptableTask
 */
public final class InterruptableMain {

  /**
   * This program creates an additional thread whose job is to print
   * 1 while it is alive and false after it is interrupted.
   * The main program, then, waits for 2 seconds and interrupts the newly
   * created thread.
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    ReentrantLock lock = new ReentrantLock();
    InterruptableTask task = new InterruptableTask();
    Thread thread = new Thread(task);
    thread.start();
    try {
      Thread.sleep(2000);
      lock.lock();
      try {
        thread.interrupt();
      } finally {
        lock.unlock();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * This class should not be instantiated.
   */
  private InterruptableMain() {
  }
}
