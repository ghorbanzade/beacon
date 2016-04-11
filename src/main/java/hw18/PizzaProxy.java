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
 *
 *
 * @author Pejman Ghorbanzade
 */
public class PizzaProxy implements PizzaImage {

  /**
   *
   */
  private PizzaReal pizza;
  private final ReentrantLock lock;
  private Condition ready;
  
  /**
   *
   */
  public PizzaProxy() {
    this.lock = new ReentrantLock();
    this.ready = this.lock.newCondition();
  }
  
  /**
   *
   *
   * @param pizza
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
   *
   *
   * @return
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
   *
   *
   * @return
   */
  public String getPizza() {
    String out = null;
    this.lock.lock();
    try {
      if (this.pizza == null) {
        this.ready.await();
      }
      out = this.pizza.getPizza();
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.lock.unlock();
    }
    return out;
  }

}
