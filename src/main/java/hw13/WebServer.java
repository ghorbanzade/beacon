//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public class WebServer {

  /**
   *
   */
  private final AccessCounter accessCounter;

  /**
   *
   */
  public WebServer() {
    this.accessCounter = new AccessCounter();
  }

  /**
   *
   *
   * @param name
   */
  public void request(String name) {
    System.out.printf("requesting %s, ", name);
    Path page = Paths.get(name);
    this.accessCounter.increment(page);
    int count = this.accessCounter.getCount(page);
    System.out.printf("count: %d%n", count);
  }

  /**
   *
   *
   * @return
   */
  public AccessCounter getAccessCounter() {
    return this.accessCounter;
  }

}
