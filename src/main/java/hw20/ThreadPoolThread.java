//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw20;

import java.util.Arrays;

/**
 *
 *
 *
 */
public class ThreadPoolThread extends Thread {

  /**
   *
   */
  private final TaskQueue tq;
  private final int id;
  private boolean flag = false;

  /**
   *
   *
   * @param tq
   * @param id
   */
  public ThreadPoolThread(TaskQueue tq, int id) {
    this.tq = tq;
    this.id = id;
  }

  /**
   *
   */
  public void setFlag() {
    this.flag = true;
  }

  /**
   *
   *
   * @param args
   */
  public void log(Object... args) {
    Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
    String message = String.format((String) args[0], arguments);
    System.out.printf("Thread %d: %s%n", this.id, message);
  }

  /**
   *
   */
  public void run() {
    this.log("starts");
    while (!this.flag) {
      Task task = tq.get();
      if (task == null) {
        this.log("is being stopped due to interrupted exception");
        continue;
      } else {
        this.log("executes a task");
        task.run();
        this.log("finishes executing a task");
      }
    }
    this.log("stops");
  }

}
