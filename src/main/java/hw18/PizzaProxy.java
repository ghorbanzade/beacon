//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw18;

import java.lang.InterruptedException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class provides an implementation of pizza image which defines
 * a placeholder for a real pizza until it is out of the oven.
 *
 * @author Pejman Ghorbanzade
 */
public class PizzaProxy implements PizzaImage {

  /**
   * A pizza proxy holds a real pizza object which does not have any
   * name initially until it is out of the oven. A pizza proxy will
   * also have a condition object which changes state once the real
   * pizza object is fully constructed.
   */
  private PizzaReal pizza;
  private final ReentrantLock lock;
  private Condition ready;

  /**
   * A pizza proxy initializes the lock and the condition to allow
   * safe threading functionality for its methods.
   */
  public PizzaProxy() {
    this.lock = new ReentrantLock();
    this.ready = this.lock.newCondition();
  }

  /**
   * This method is used by client to set an order in thread-safe way.
   *
   * @param pizza the real pizza to be made
   */
  public void setRealPizza(PizzaReal pizza) {
    this.lock.lock();
    try {
      if (this.pizza == null) {
        this.pizza = pizza;
        this.ready.signalAll();
      }
    } finally {
      this.lock.unlock();
    }
  }

  /**
   * This method uses a reentrant lock to check for a thread in case
   * the real pizza associated with this object is ready for pick up.
   * Client objects use this method to check if their orders are ready.
   *
   * @return whether the real pizza object is out of the oven or not
   */
  public boolean isReady() {
    boolean out = false;
    while (true) {
      this.lock.lock();
      try {
        if (this.pizza != null) {
          out = true;
        }
        break;
      } finally {
        this.lock.unlock();
      }
    }
    return out;
  }

  /**
   * This method is called when the client asks for his pizza order.
   * In case the pizza is not ready yet, the client will have to wait
   * until the it changes the state.
   *
   * @return the name of the ordered pizza 
   */
  public String getPizza() {
    String out = null;
    this.lock.lock();
    try {
      while (this.pizza == null) {
        this.ready.await();
      }
      out = this.pizza.getPizza();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.lock.unlock();
    }
    return out;
  }

}
