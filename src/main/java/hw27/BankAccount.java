//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

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
  private double balance = 0;

  /**
   *
   */
  public double getBalance() {
    return this.balance;
  }

  /**
   *
   */
  public double deposit(double amount) {
    this.balance += amount;
    return this.balance;
  }

  /**
   *
   */
  public double withdraw(double amount) {
    this.balance -= amount;
    return this.balance;
  }

}
