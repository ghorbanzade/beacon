//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

import java.util.ArrayList;

public class CreditAccount extends BankAccount {

  private CreditAccountType type;
  private float currentlimit;
  private float maxLimit;
  private ArrayList<CreditCard> cards = new ArrayList<CreditCard>();

  public CreditAccount(Bank bank, Customer customer,
                       CreditAccountType type, float limit) {
    super(bank, customer);
    this.type = type;
    this.currentLimit = limit;
    this.maxLimit = limit;
  }

  @Override
  public void withdraw(Client client, float amount)
      throws UnsupportedOperationException {
    super.authorize(client);
    if (this.getBalance() - amount > this.currentLimit) {
      super.withdraw(client, amount);
    } else {
      throw new UnsupportedOperationException(
          "amount exceeded current credit limit"
      );
    }
  }

  public void setCreditLimit(Client client, float limit)
      throws UnsupportedOperationException {
    super.authorize(client);
    if (limit < this.maxLimit) {
      this.currentLimit = limit;
    } else {
      throw new UnsupportedOperationException(
          "limit exceeds maximum credit limit"
      );
    }
  }

  public float getCreditLimit(Client client)
      throws UnsupportedOperationException {
    super.authorize(client);
    return this.limit;
  }

  public CreditAccountType getType(Client client)
      throws UnsupportedOperationException {
    super.authorize(client);
    return this.type;
  }
}
