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
 * @see ThreadPool
 */
public final class ThreadPoolMain {

  /**
   *
   *
   * @param args command line arguments given to this program
   */
  public static void main(String[] args) {
    ThreadPool.getInstance().execute(new Task("A"));
    ThreadPool.getInstance().execute(new Task("B"));
    ThreadPool.getInstance().execute(new Task("C"));
    ThreadPool.getInstance().execute(new Task("D"));
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ThreadPool.getInstance().shutdown();
  }

  /**
   * This class must not be instantiated.
   */
  private ThreadPoolMain() {
  }

}
