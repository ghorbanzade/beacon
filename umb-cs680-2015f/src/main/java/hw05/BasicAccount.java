//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw05;

/**
*
*
* @author Pejman Ghorbanzade
*/
public class BasicAccount extends BankAccount {
  /**
  *
  */
  private final BasicAccountType type;

  /**
  *
  *
  * @param bank
  * @param customer
  * @param type
  */
  public BasicAccount(Bank bank, Customer customer,
                      BasicAccountType type) {
    super(bank, customer);
    this.type = type;
  }

  /**
  *
  *
  * @param client
  * @param amount
  */
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

  /**
  *
  *
  * @param client
  * @return
  */
  public BasicAccountType getType(Client client)
      throws UnsupportedOperationException {
    super.authorize(client);
    return this.type;
  }
}
