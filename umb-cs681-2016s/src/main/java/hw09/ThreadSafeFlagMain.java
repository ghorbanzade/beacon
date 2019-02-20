//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw09;

/**
 * This class provides a thread safe implementation of the given
 * ThreadUnsafeFlag problem.
 *
 * @author Pejman Ghorbanzade
 * @see ThreadSafeFlag
 * @see Interrupter
 */
public final class ThreadSafeFlagMain {

  /**
   * This program creates a thread and starts it before invoking the
   * work method of the ThreadSafeFlag object.
   *
   * <p>Interestingly, since the new thread first takes hold of the lock
   * the main thread will not find the chance to print any '#' characters
   * and the call to work produces no output other than an indication that
   * the flag was set by the new thread.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    ThreadSafeFlag obj = new ThreadSafeFlag();
    Thread thread = new Thread(obj.getInterrupter());
    thread.start();
    obj.work();
  }

  /**
   * This class should not be instantiated.
   */
  private ThreadSafeFlagMain() {
  }

}
