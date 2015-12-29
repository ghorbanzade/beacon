//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

import java.util.ArrayList;

/**
*
*
* @author Pejman Ghorbanzade
*/
public abstract class BankAccount {
  /**
  *
  */
  private float balance;
  private final Bank bank;
  private final ArrayList<Customer> customers = new ArrayList<Customer>();

  /**
  *
  *
  * @param bank
  * @param customer
  */
  public BankAccount(Bank bank, Customer customer) {
    this.balance = 0;
    this.bank = bank;
    this.customers.add(customer);
  }

  /**
  *
  *
  * @param client
  * @param amount
  */
  public void deposit(Client client, float amount) {
    this.balance += amount;
  }

  /**
  *
  *
  * @param client
  * @param amount
  */
  public void withdraw(Client client, float amount)
      throws UnsupportedOperationException {
    authorize(client);
    this.balance -= amount;
  }

  /**
  *
  *
  * @param client
  * @return
  */
  public float getBalance(Client client)
      throws UnsupportedOperationException {
    authorize(client);
    return this.balance;
  }

  /**
  *
  *
  * @param client
  * @return
  */
  public Bank getBanke(Client client)
      throws UnsupportedOperationException {
    authorize(client);
    return this.bank;
  }

  /**
  *
  *
  * @param client
  * @param customer
  */
  public void addCustomer(Client client, Customer customer)
      throws UnsupportedOperationException {
    authorize(client);
    this.customers.add(customer);
  }

  /**
  *
  *
  * @param client
  */
  public void authorize(Client client)
      throws UnsupportedOperationException {
    boolean isAuthorized = false;
    if (client instanceof Customer) {
      Customer customer = (Customer) client;
      if (this.customers.contains(customer)) {
        isAuthorized = true;
      }
    }
    if (!isAuthorized) {
      throw new UnsupportedOperationException("client unauthorized");
    }
  }
}
