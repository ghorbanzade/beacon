//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw20;

/**
 * This class defines a task as the runnable object passed to a thread
 * for execution.
 *
 * @author Pejman Ghorbanzade
 * @see TaskQueue
 */
public class Task implements Runnable {

  /**
   * A task has a name which it prints once it is executed.
   */
  private final String name;

  /**
   * To create a task, name is specified which is used when the task is
   * executed.
   *
   * @param name name of the task
   */
  public Task(String name) {
    this.name = name;
  }

  /**
   * A task would simply print its name when it is executed.
   */
  public void run() {
    System.out.printf("Task %s: was executed.%n", name);
  }

}
