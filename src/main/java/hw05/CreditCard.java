//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

public class CreditCard {

  private final CreditAccount account;
  private final Customer customer;
  private float limit;
  private float rewards;

  public CreditCard(CreditAccount account, Customer customer) {
    this.account = account;
    this.customer = customer;
    this.limit = account.getCreditLimit(customer);
  }

  public void purchase(Client client, float amount)
      throws UnsupportedOperationException {
    this.authorize(client);
    this.account.withdraw(client, amount);
    this.rewards += amount * 0.05F;
  }

  public void setCreditLimit(Client client, float limit)
      throws UnsupportedOperationException {
    this.authorize(client);
    if (limit < this.account.getCreditLimit(client)) {
      this.limit = limit;
    } else {
      throw new UnsupportedOperationException(
          "limit exceeds maximum credit limit"
      );
    }
  }

  public float getCreditLimit(Client client)
      throws UnsupportedOperationException {
    this.authorize(client);
    return this.limit;
  }

  public float getRewards(Client client)
      throws UnsupportedOperationException {
    this.authorize(client);
    return this.rewards;
  }

  public void redeem(Client client, float amount)
      throws UnsupportedOperationException {
    this.authorize(client);
    if (amount <= this.rewards) {
      this.rewards -= amount;
      this.account.deposit(client, amount);
    } else {
      throw new UnsupportedOperationException(
        "amount exceeds total available rewards"
      );
    }
  }

  private void authorize(Client client)
      throws UnsupportedOperationException {
    boolean isAuthorized = false;
    if (client instanceof Customer) {
      Customer customer = (Customer) client;
      if (this.customer.equals(customer)) {
        isAuthorized = true;
      }
    }
    if (!isAuthorized) {
      throw new UnsupportedOperationException(
        "unauthorized client"
      );
    }
  }
}
