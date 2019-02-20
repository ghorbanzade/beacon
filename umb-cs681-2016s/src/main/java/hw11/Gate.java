//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw11;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class defines a gate object as a monitoring tool that keeps track
 * of the number of people inside a building. Any object of this class
 * provides methods for entering and exiting personnel.
 *
 * @author Pejman Ghorbanzade
 * @see Guest
 */
public class Gate {

  /**
   * A gate object keeps track of the number of people inside.
   */
  private final AtomicInteger counter;
 
  /**
   * The constructor initializes an atomic integer that holds the number
   * of people inside.
   */
  public Gate() {
    this.counter = new AtomicInteger();
  }

  /**
   * This method simply increments the counter attribute in a thread-safe
   * manner.
   */
  public void enter() {
    this.counter.incrementAndGet();
  }

  /**
   * This method simply decrements the counter attribute in a thread-safe
   * manner.
   */
  public void exit() {
    this.counter.decrementAndGet();
  }

  /**
   * This method simply returns the number of people inside.
   *
   * @return the number of people currently inside the building
   */
  public int getCount() {
    return this.counter.get();
  }

}
