//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw25;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class defines an access counter object as part of any web
 * server, whose job is to keep track of the number of times webpages
 * are requested.
 *
 * @author Pejman Ghorbanzade
 */
public class AccessCounter {

  private final ConcurrentHashMap<Path, Integer> hm;

  /**
   * Creates an empty concurrent hash map to keep track of how many times
   * different files are requested.
   */
  public AccessCounter() {
    this.hm = new ConcurrentHashMap<Path, Integer>();
  }

  /**
   * Increments the number of times a given webpage is requested.
   *
   * @param path the webpage requested by client
   */
  public void increment(Path path) {
    int count = this.hm.getOrDefault(path, 0);
    this.hm.put(path, count + 1);
  }

  /**
   * Returns the number of times a given webpage has been requested so far.
   *
   * @param path the webpage requested by client
   * @return number of times a webpage is requested
   */
  public int getCount(Path path) {
    return this.hm.getOrDefault(path, 0);
  }

  /**
   * Prints statistics about webpages requested so far.
   */
  public void showStats() {
    System.out.println(this.hm.entrySet());
  }

}
