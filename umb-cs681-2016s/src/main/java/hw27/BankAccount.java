//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw27;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Defines the bank account for a client to which money can be deposited and
 * from which money can be withdrawn.
 *
 * @author Pejman Ghorbanzade
 * @see BankServer
 */
public class BankAccount {

  private float balance = 0;
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private final Lock readLock = lock.readLock();
  private final Lock writeLock = lock.writeLock();

  /**
   * Returns current available balance for this account.
   *
   * @return the current available balance for this account
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
   * Adds a given amount to this account and returns the remaining balance
   * for this account.
   *
   * @param amount amount to be added to this account
   * @return balance of the account after deposit operation
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
   * Deducts a given amount from this account and returns the remaining
   * balance for the account.
   *
   * @param amount amount to be deducted from this account
   * @return balance of the account after withdrawal operation
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
