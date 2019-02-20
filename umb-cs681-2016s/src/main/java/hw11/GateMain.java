//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw11;

/**
 * This class demonstrates a simple thread-safe solution to keep track
 * of the number of passings in and out of the building by two guests.
 *
 * @author Pejman Ghorbanzade
 * @see Gate
 * @see Guest
 */
public final class GateMain {

  /**
   * This program creates two guest objects that are passed to two threads
   * that allow people to enter and exit a building from a single gate.
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    Gate gate = new Gate();
    Guest guest1 = new Guest(gate);
    Guest guest2 = new Guest(gate);
    Thread thread1 = new Thread(guest1);
    Thread thread2 = new Thread(guest2);
    thread1.start();
    thread2.start();
  }

  /**
   * This class should not be instantiated.
   */
  private GateMain() {
  }

}
