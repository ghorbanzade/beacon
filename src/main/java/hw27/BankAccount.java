//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see BankServer
 */
public class BankAccount {

  /**
   *
   */
  private float balance = 0;
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private final Lock readLock = lock.readLock();
  private final Lock writeLock = lock.writeLock();

  /**
   *
   *
   * @return
   */
  public float getBalance() {
    float out = 0; 
    this.readLock.lock();
    try {
      out = this.balance;
    } finally {
      this.readLock.unlock();
    }
    return out;
  }

  /**
   *
   *
   * @param amount
   * @return
   */
  public double deposit(double amount) {
    this.writeLock.lock();
    try {
      this.balance += amount;
    } finally {
      this.writeLock.unlock();
    }
    return this.getBalance();
  }

  /**
   *
   *
   * @param amount
   * @return
   */
  public double withdraw(double amount) {
    this.writeLock.lock();
    try {
      this.balance -= amount;
    } finally {
      this.writeLock.unlock();
    }
    return this.getBalance();
  }

}
