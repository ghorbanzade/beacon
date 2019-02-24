//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class defines an access counter object as part of any web
 * server, whose job is to keep track of the number of times webpages
 * are requested.
 *
 * @author Pejman Ghorbanzade
 */
public class AccessCounter {

  /**
   * An access counter object keeps record of the number of times
   * a webpage is requested.
   */
  private final ReentrantLock lock;
  private final HashMap<Path, Integer> hm;

  /**
   * The constructor initializes an empty hash table and a lock
   * that ensures threads update table entries sequentially.
   */
  public AccessCounter() {
    this.lock = new ReentrantLock();
    this.hm = new HashMap<Path, Integer>();
  }

  /**
   * This method takes a webpage and increments the number of
   * times it is requested.
   *
   * @param path the webpage requested by client
   */
  public void increment(Path path) {
    int count = 0;
    this.lock.lock();
    try {
      if (this.hm.containsKey(path)) {
        count = this.hm.get(path);
      }
      this.hm.put(path, count + 1);
    } finally {
      this.lock.unlock();
    }
  }

  /**
   * This method takes a webpage and returns the number of times
   * the webpage has been requested so far.
   *
   * @param path the webpage requested by client
   * @return number of times a webpage is requested
   */
  public int getCount(Path path) {
    int count = 0;
    this.lock.lock();
    try {
      if (this.hm.containsKey(path)) {
        count = this.hm.get(path);
      }
    } finally {
      this.lock.unlock();
    }
    return count;
  }

  /**
   * This method simply returns current status of the hashmap
   * attribute.
   */
  public void showStats() {
    System.out.println(this.hm.entrySet());
  }

}
