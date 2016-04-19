//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw20;

/**
 * This class demonstrates how a singleton threadpool can be used
 * to execute a number of tasks using a specified number of threads.
 *
 * @author Pejman Ghorbanzade
 * @see ThreadPool
 */
public final class ThreadPoolMain {

  /**
   * This program creates a number of tasks with different names
   * and a thread pool of certain size for executing those tasks.
   * It then waits for a certain amount of time before stopping
   * all the threads.
   *
   * @param args command line arguments given to this program
   */
  public static void main(String[] args) {
    ConfigReader cr = new ConfigReader("/threadpool.properties");
    for (int i = 1; i <= Integer.parseInt(cr.get("tasks.number")); i++) {
      ThreadPool.getInstance().execute(new Task(getId(i)));
    }
    try {
      Thread.sleep(Integer.parseInt(cr.get("main.thread.timeout")));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ThreadPool.getInstance().shutdown();
  }

  /**
   * This method converts an id number to a corresponding string literal
   * such that 26 will be AA and 28 will be AC.
   *
   * @param num the numeric 
   * @return a string literal of an id number
   */
  private static String getId(int num) {
    StringBuilder sb = new StringBuilder();
    while (num > 0) {
      num--;
      sb.insert(0, (char) ('A' + (num % 26))); 
      num = num / 26;
    }
    return sb.toString();
  }

  /**
   * This class must not be instantiated.
   */
  private ThreadPoolMain() {
  }

}
