//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

import java.util.ArrayList;

public abstract class BankAccount {

  private float balance;
  private final Bank bank;
  private final ArrayList<Customer> customers = new ArrayList<Customer>();

  public BankAccount(Bank bank, Customer customer) {
    this.balance = 0;
    this.bank = bank;
    this.customers.add(customer);
  }

  public void deposit(Client client, float amount) {
    this.balance += amount;
  }

  public void withdraw(Client client, float amount)
      throws UnsupportedOperationException {
    authorize(client);
    this.balance -= amount;
  }

  public float getBalance(Client client)
      throws UnsupportedOperationException {
    authorize(client);
    return this.balance;
  }

  public Bank getBanke(Client client)
      throws UnsupportedOperationException {
    authorize(client);
    return this.bank;
  }

  public void addCustomer(Client client, Customer customer)
      throws UnsupportedOperationException {
    authorize(client);
    this.customers.add(customer);
  }

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
