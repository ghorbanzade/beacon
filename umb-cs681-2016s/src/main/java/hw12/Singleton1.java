//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class demonstrates a thread-safe design of the singleton class
 * with lazy instantiation using locks.
 *
 * @author Pejman Ghorbanzade
 * @see Agent
 */
public final class Singleton1 {

  /**
   * This singleton class has a single instance which is initially null
   * and a lock to prevent multiple threads to initialize the instance
   * at the same time.
   */
  private static Singleton1 instance = null;
  private static ReentrantLock lock = new ReentrantLock();

  /**
   * This singleton class must not be instantiated.
   */
  private Singleton1() {
  }

  /**
   * This static method provides access to the only instance of this singleton
   * class, in a thread-safe manner.
   *
   * @return the single instance of Singleton1 class
   */
  public static Singleton1 getInstance() {
    lock.lock();
    try {
      if (instance == null) {
        instance = new Singleton1();
      }
    } finally {
      lock.unlock();
    }
    return instance;
  }

}
