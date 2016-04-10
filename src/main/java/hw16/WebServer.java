//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw16;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A webserver is a wrapper object that holds an instance of
 * the accesss counter.
 *
 * @author Pejman Ghorbanzade
 * @see AccessCounter
 */
public class WebServer {

  /**
   * A webserver is just a wrapper object that holds an instance
   * of access counter.
   */
  private final AccessCounter accessCounter;
  private final ReentrantLock lock;

  /**
   * The constructor simply initializes the access counter and lock
   * of the server.
   */
  public WebServer() {
    this.accessCounter = new AccessCounter();
    this.lock = new ReentrantLock();
  }

  /**
   * This method takes name of a webpage and processes the request
   * by incrementing the access counter and printing the number of times
   * that same webpage had been asked for.
   *
   * @param name the name of the requested webpage
   */
  public void request(String name) {
    this.lock.lock();
    try {
      Path page = Paths.get(name);
      System.out.printf("requesting: %14s\t", name);
      this.accessCounter.increment(page);
      int count = this.accessCounter.getCount(page);
      System.out.printf("count: %d%n", count);
    } finally {
      this.lock.unlock();
    }
  }

  /**
   * This method allows direct access to access counter of the webserver
   * to show the entries of in its hashmap at a given moment.
   *
   * @return the access counter of the webserver
   */
  public AccessCounter getAccessCounter() {
    return this.accessCounter;
  }

}
