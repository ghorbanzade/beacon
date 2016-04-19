//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw20;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see Task
 */
public class TaskQueue {

  /**
   *
   */
  private final ArrayList<Task> queue = new ArrayList<Task>();
  private final ReentrantLock lock = new ReentrantLock();
  private final Condition condition;

  /**
   *
   */
  public TaskQueue() {
    condition = this.lock.newCondition();
  }

  /**
   *
   *
   * @return
   */
  public int size() {
    while (true) {
      this.lock.lock();
      try {
        return queue.size();
      } finally {
        this.lock.unlock();
      }
    }
  }

  /**
   *
   *
   * @param
   */
  public void put(Task task) {
    this.lock.lock();
    try {
      this.queue.add(task);
      System.out.println("Queue: a task queued.");
      this.condition.signalAll();
    } finally {
      this.lock.unlock();
    }
  }

  /**
   *
   *
   * @return
   */
  public Task get() {
    this.lock.lock();
    try {
      while (this.queue.isEmpty()) {
        System.out.println("Queue: waiting for a task...");
        this.condition.await();
      }
      System.out.println("Queue: a task dequeued.");
      return this.queue.remove(0);
    } catch (InterruptedException e) {
      return null;
    } finally {
      this.lock.unlock();
    }
  }

}
