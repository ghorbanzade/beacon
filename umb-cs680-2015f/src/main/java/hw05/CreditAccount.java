//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw05;

import java.util.ArrayList;

/**
*
*
* @author Pejman Ghorbanzade
*/
public class CreditAccount extends BankAccount {
  /**
  *
  */
  private final CreditAccountType type;
  private final float limit;
  private final ArrayList<CreditCard> cards = new ArrayList<CreditCard>();

  /**
  *
  *
  * @param bank
  * @param customer
  * @param type
  * @param limit
  */
  public CreditAccount(Bank bank, Customer customer,
                       CreditAccountType type, float limit) {
    super(bank, customer);
    this.type = type;
    this.limit = limit;
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
    if (this.getBalance(client) - amount + this.limit > 0) {
      super.withdraw(client, amount);
    } else {
      throw new UnsupportedOperationException(
          "amount exceeded current credit limit"
      );
    }
  }

  /**
  *
  *
  * @param customer
  * @return
  */
  public CreditCard addCreditCard(Customer customer)
      throws UnsupportedOperationException {
    super.authorize(customer);
    CreditCard card = new CreditCard(this, customer);
    this.cards.add(card);
    return card;
  }

  /**
  *
  *
  * @param client
  * @return
  */
  public float getCreditLimit(Client client)
      throws UnsupportedOperationException {
    super.authorize(client);
    return this.limit;
  }

  /**
  *
  *
  * @param client
  * @return
  */
  public CreditAccountType getType(Client client)
      throws UnsupportedOperationException {
    super.authorize(client);
    return this.type;
  }
}
