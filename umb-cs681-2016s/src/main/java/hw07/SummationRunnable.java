//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class defines the object to be passed to the thread.
 *
 * @author Pejman Ghorbanzade
 */
public class SummationRunnable implements Runnable {

  /**
   * every object of this class, has a counter that specifies the number
   * of times to print numbers and a flag that signals if the thread
   * handling the object should terminate.
   * 
   * <p>In addition, an object has a lock attribute that prevents
   * simultaneous access of multiple threads to shared variables.
   */
  private int counter;
  private final ReentrantLock lock;
  private boolean flag = false;

  /**
   * User should initialize the counter of the object to be constructed.
   *
   * @param counter the numer of integers a thread will print.
   */
  public SummationRunnable(int counter) {
    this.lock = new ReentrantLock();
    this.counter = counter;
  }

  /**
   * This method implements the run method of Runnable interface.
   * The first thread that holds the lock, continues to prints decremented
   * values of counter every one second until the counter becomes negative
   * which causes the thread to die.
   */
  public void run() {
    while (true) {
      this.lock.lock();
      try {
        if (this.flag) {
          break;
        }
        System.out.printf("%d%n", this.counter--);
        Thread.sleep(1000);
        if (this.counter < 0) {
          System.out.printf("print done%n");
          return;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        this.lock.unlock();
      }
    }
    System.out.printf("Stopped by main()!%n");
    this.flag = false;
  }

  /**
   * This method safely sets the flag that signals thread to exit the
   * running state.
   */
  public void setFlag() {
    this.lock.lock();
    try {
      this.flag = true;
    } finally {
      this.lock.unlock();
    }
  }

}
