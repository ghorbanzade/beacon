//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw12;

/**
 * This class demonstrates how singleton instances can be accessed from two
 * different threads.
 *
 * @author Pejman Ghorbanzade
 * @see Agent
 * @see Singleton1
 * @see Singleton2
 */
public final class SingletonMain {

  /**
   * This program creates two threads that are passed two agent objects and
   * initiated to access singleton class.
   * 
   * <p>If the singleton classes are thread-safe, the two threads will print
   * identical instance references for each Singleton class.
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    Agent agent1 = new Agent();
    Agent agent2 = new Agent();
    Thread thread1 = new Thread(agent1);
    Thread thread2 = new Thread(agent2);
    thread1.start();
    thread2.start();
  }

  /**
   * This class must not be instantiated.
   */
  private SingletonMain() {
  }

}
