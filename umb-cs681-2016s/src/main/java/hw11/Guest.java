//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw11;

/**
 * This class defines a guest as an object to be passed to a thread that
 * can invoke enter can exit methods on a gate.
 *
 * @author Pejman Ghorbanzade
 * @see Gate
 */
public class Guest implements Runnable {

  /**
   * A guest object has a gate from which it can enter and exit.
   */
  private final Gate gate;

  /**
   * The constructor for a guest takes the gate from which the guest
   * trespasses to call enter and exit on it.
   *
   * @param gate the gate from which the guest trespasses
   */
  public Guest(Gate gate) {
    this.gate = gate;
  }

  /**
   * Once passed to a thread, a guest object will make multiple enter and
   * exit calls on the gate it is instantiated with.
   */
  public void run() {
    for (int i = 0; i < 1000; i++) {
      this.gate.enter();
      this.gate.exit();
      System.out.println(this.gate.getCount());
    }
  }

}
