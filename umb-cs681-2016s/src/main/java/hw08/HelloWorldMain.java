//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw08;

import java.util.Date;

/**
 * This class demonstrates passing of a lambda expression to a thread.
 *
 * @author Pejman Ghorbanzade
 */
public final class HelloWorldMain {

  /**
   * The main method simply declares a thread and starts it.
   * The thread is passed a lambda expression that follows the
   * logic in GreetingRunnable class given in the question.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    Thread thread = new Thread(()-> {
      try {
        for (int i = 0; i < 10; i++) {
          System.out.printf("%s: Greetings!%n", new Date());
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        System.out.println("Thread interrupted");
      }
    });
    thread.start();
  }

  /**
   * this class should not be instantiated.
   */
  private HelloWorldMain() {
  }

}
