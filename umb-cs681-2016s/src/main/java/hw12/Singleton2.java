//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class demonstrates a thread-safe design of a singleton class
 * with lazy instantiation without using a lock.
 *
 * @author Pejman Ghorbanzade
 * @see Agent
 */
public final class Singleton2 {

  /**
   * This class allows lazy instantiation of singleton instance in a thread
   * safe manner.
   */
  private static class InstanceHolder {
    private static final Singleton2 instance = new Singleton2();
  }

  /**
   * This singleton class must not be instantiated.
   */
  private Singleton2() {
  }

  /**
   * This method provides access to the single instance of this singleton
   * class, in a thread-safe manner.
   *
   * @return the single instance of Singleton2 class
   */
  public static Singleton2 getInstance() {
    return InstanceHolder.instance;
  }

}
