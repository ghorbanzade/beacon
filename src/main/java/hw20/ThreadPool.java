//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw20;

import java.util.Vector;

/**
 * A threadpool is a singleton class that creates as many threads as
 * specified in the configuration file and places them in a queue, the
 * first time it is instantiated. The singleton class provides methods
 * to use those threads to execute given tasks.
 *
 * @author Pejman Ghorbanzade
 * @see ThreadPoolThread
 */
public final class ThreadPool {

  /**
   * The thread pool instance has a vector on which it places its threads
   * and a queue on which it places the tasks awaiting execution by those
   * threads.
   */
  private static ThreadPool instance;
  private TaskQueue queue = null;
  private Vector<ThreadPoolThread> availableThreads = null;

  /**
   * This singleton class uses lazy instantiation to avoid the expensive
   * cost of creating several threads in case no task is planned to be
   * executed.
   *
   * @return the single instance of this class
   */
  public static ThreadPool getInstance() {
    if (instance == null) {
      instance = new ThreadPool();
    }
    return instance;
  }

  /**
   * Singleton class should not be directly instantiated.
   */
  private ThreadPool() {
    ConfigReader cr = new ConfigReader("/threadpool.properties");
    int maxThreadNum = Integer.parseInt(cr.get("max.threads"));
    queue = new TaskQueue();
    availableThreads = new Vector<ThreadPoolThread>();
    for (int i = 0; i < maxThreadNum; i++) {
      ThreadPoolThread th = new ThreadPoolThread(queue, i);
      availableThreads.add(th);
      th.start();
    }
  }

  /**
   * This method is called by user when he needs a task to be executed.
   * The task is placed on a queue waiting to be executed as soon as a
   * thread is available.
   *
   * @param task the task to be executed
   */
  public void execute(Task task) {
    this.queue.put(task);
  }

  /**
   * This method allows user to see how many tasks are on the queue
   * awaiting executino.
   *
   * @return the number of tasks on the queue awaiting execution
   */
  public int getTaskQueueSize() {
    return this.queue.size();
  }

  /**
   * This method allows user to see how many threads exist in the
   * pool.
   *
   * @return the number of threads available to execute tasks
   */
  public int getThreadPoolSize() {
    return this.availableThreads.size();
  }

  /**
   * This method is called by user to stop all execution of all
   * threads which itself will lead to a halt on execution of all
   * tasks.
   */
  public void shutdown() {
    for (ThreadPoolThread t: availableThreads) {
      t.setFlag();
      t.interrupt();
    }
  }

}
