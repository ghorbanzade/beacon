//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

public class BasicAccount extends BankAccount {

  private final BasicAccountType type;

  public BasicAccount(Bank bank, Customer customer,
                      BasicAccountType type) {
    super(bank, customer);
    this.type = type;
  }

  @Override
  public void withdraw(Client client, float amount)
      throws UnsupportedOperationException {
    super.authorize(client);
    if (this.getBalance(client) > amount) {
      super.withdraw(client, amount);
    } else {
      throw new UnsupportedOperationException(
          "insufficient balance"
      );
    }
  }

  public BasicAccountType getType(Client client)
      throws UnsupportedOperationException {
    super.authorize(client);
    return this.type;
  }
}
