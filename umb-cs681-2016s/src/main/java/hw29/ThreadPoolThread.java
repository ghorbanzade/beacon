//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw29;

import java.util.Arrays;

/**
 * This class defines the member threads of a thread pool instance.
 * Each member thread has a unique identifier and a flag that can
 * be set to indicate it should stop. It also has access to the
 * queue of tasks that need to be executed.
 *
 * @author Pejman Ghorbanzade
 * @see ThreadPool
 * @see TaskQueue
 */
public class ThreadPoolThread extends Thread {

  /**
   * A threadpool member has an ID and a flag that can be set to
   * indicate it should be stopped. It also knows which queue it
   * should fetch the program tasks from.
   */
  private final TaskQueue tq;
  private final int id;
  private boolean flag = false;

  /**
   * To createa a threadpool member, the task on which it should
   * operate and the id that should be assigned to it must
   * be specified.
   *
   * @param tq the queue from which this object should take its tasks
   * @param id a unique identifier for the threadpool member
   */
  public ThreadPoolThread(TaskQueue tq, int id) {
    this.tq = tq;
    this.id = id;
  }

  /**
   * This method is used by a producer (main thread) to indicate
   * the thread should stop fetching tasks and executing them.
   */
  public void setFlag() {
    this.flag = true;
  }

  /**
   * This utility method allows printing a status message for the
   * thread member while indicating its id for information purposes.
   *
   * @param args parameters to format the message string
   */
  private void log(Object... args) {
    Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
    String message = String.format((String) args[0], arguments);
    System.out.printf("Thread %d: %s%n", this.id, message);
  }

  /**
   * When a threadpool member is started, it stays alive as long as
   * the main thread has not hinted it to halt. While running, it
   * continually checks if there are task in the queue and if there
   * is any task, it fetches the task and executes it.
   */
  public void run() {
    this.log("starts");
    while (!this.flag) {
      ConnectionHandler task = tq.get();
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
