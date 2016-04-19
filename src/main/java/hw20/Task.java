//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw20;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public class Task implements Runnable {

  /**
   *
   */
  private final String arg;

  /**
   *
   *
   * @param arg
   */
  public Task(String arg) {
    this.arg = arg;
  }

  /**
   *
   */
  public void run() {
    System.out.printf("Running task %s%n", arg);
  }

}
