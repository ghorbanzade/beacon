//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw12;

/**
 * This class defines an agent as a class that implements the runnable class.
 *
 * @author Pejman Ghorbanzade
 * @see Singleton1
 * @see Singleton2
 */
public class Agent implements Runnable {

  /**
   * An agent object is a dummy object that can be passed to a thread object.
   */
  public Agent() {
    // Intentionally left empty. No action needed here.
  }

  /**
   * Each running thread will simply access instances of two singleton classes
   * with different design.
   */
  public void run() {
    System.out.println("Singleton1: " + Singleton1.getInstance());
    System.out.println("Singleton2: " + Singleton2.getInstance());
  }

}
