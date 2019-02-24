//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw07;

/**
 * This class demonstrates using a simple thread concurrent with
 * the main thread.
 *
 * @author Pejman Ghorbanzade
 * @see SummationRunnable
 */
public final class SummationMain {

  /**
   * This program initiates a thread to print numbers 10 to 0 while
   * making sure that the thread is not destroyed by the main thread.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SummationRunnable runnable = new SummationRunnable(10);
    Thread thread = new Thread(runnable);
    thread.start();
    for (int i = 0; i < 5; i++) {
      System.out.printf("#%n");
    }
    runnable.setFlag();
    try {
      thread.join();
      new Thread(runnable).start();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * this class should not be instantiated.
   */
  private SummationMain() {
  }

}
