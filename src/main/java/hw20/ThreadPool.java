//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw20;

import java.util.Vector;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public final class ThreadPool {

  /**
   *
   */
  private static ThreadPool instance;
  private TaskQueue queue = null;
  private Vector<ThreadPoolThread> availableThreads = null;

  /**
   *
   *
   * @return
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
   *
   *
   * @param task
   */
  public void execute(Task task) {
    this.queue.put(task);
  }

  /**
   *
   *
   * @return
   */
  public int getTaskQueueSize() {
    return this.queue.size();
  }

  /**
   *
   *
   * @return
   */
  public int getThreadPoolSize() {
    return this.availableThreads.size();
  }

  /**
   *
   */
  public void shutdown() {
    for (ThreadPoolThread t: availableThreads) {
      t.setFlag();
      t.interrupt();
    }
  }

}
