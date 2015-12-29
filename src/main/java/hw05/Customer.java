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
public class Customer extends Client {
  /**
  *
  *
  */
  ArrayList<BasicAccount> basicAccounts = new ArrayList<BasicAccount>();
  ArrayList<CreditAccount> creditAccounts = new ArrayList<CreditAccount>();
  ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();

  /**
  *
  *
  * @param name
  */
  public Customer(String name) {
    super(name);
  }

  /**
  *
  *
  * @param bank
  * @param type
  */
  public void newBasicAccount(Bank bank, BasicAccountType type) {
    this.basicAccounts.add(new BasicAccount(bank, this, type));
  }

  /**
  *
  *
  * @param bank
  * @param type
  */
  public void newCreditAccount(Bank bank, CreditAccountType type)
      throws UnsupportedOperationException {
    float totalBalance = 0;
    for (BasicAccount account: this.basicAccounts) {
      totalBalance += account.getBalance(this);
    }
    if (totalBalance < 1000F) {
      throw new UnsupportedOperationException(
          "insufficient balance"
      );
    } else {
      this.creditAccounts.add(
          new CreditAccount(bank, this, type, 500F)
      );
    }
  }

  /**
  *
  *
  * @param account
  */
  public void newCreditCard(CreditAccount account)
      throws UnsupportedOperationException {
    int index = this.creditAccounts.indexOf(account);
    if (index == -1) {
      throw new UnsupportedOperationException(
          "unknown credit account"
      );
    } else {
      this.creditCards.add(
          this.creditAccounts.get(index).addCreditCard(this)
      );
    }
  }

  /**
  *
  *
  * @param account
  * @return
  */
  public float getBalance(BankAccount account)
      throws UnsupportedOperationException {
    return account.getBalance(this);
  }

  /**
  *
  *
  * @param account
  * @param amount
  */
  public void deposit(BankAccount account, float amount) {
    account.deposit(this, amount);
  }

  /**
  *
  *
  * @param account
  * @param amount
  */
  public void withdraw(BankAccount account, float amount)
      throws UnsupportedOperationException {
  }

  /**
  *
  *
  * @param src
  * @param dst
  * @param amount
  * @throws UnsupportedOperationException
  */
  public void transfer(BankAccount src, BankAccount dst,
      float amount) throws UnsupportedOperationException {
    src.withdraw(this, amount);
    dst.deposit(this, amount);
  }

  /**
  *
  *
  * @param card
  * @param amount
  */
  public void purchase(CreditCard card, float amount)
      throws UnsupportedOperationException {
    card.purchase(this, amount);
  }

  /**
  *
  *
  * @param card
  */
  public float getRewards(CreditCard card)
      throws UnsupportedOperationException {
    return card.getRewards(this);
  }

  /**
  *
  *
  * @param card
  * @param amount
  * @throws UnsupportedOperationException
  */
  public void redeem(CreditCard card, float amount)
      throws UnsupportedOperationException {
    card.redeem(this, amount);
  }

  /**
  *
  *
  * @param account
  * @return
  * @throws UnsupportedOperationException
  */
  public float getCreditLimit(CreditAccount account)
      throws UnsupportedOperationException {
    return account.getCreditLimit(this);
  }

  /**
  *
  *
  * @param card
  * @return
  * @throws UnsupportedOperationException
  */
  public float getCreditLimit(CreditCard card)
      throws UnsupportedOperationException {
    return card.getCreditLimit(this);
  }

  /**
  *
  *
  * @param card
  * @param limit
  */
  public void setCreditLimit(CreditCard card, float limit)
      throws UnsupportedOperationException {
    card.setCreditLimit(this, limit);
  }

  /**
  *
  *
  * @return
  */
  public ArrayList<BasicAccount> getBasicAccounts() {
    return this.basicAccounts;
  }

  /**
  *
  *
  * @return
  */
  public ArrayList<CreditAccount> getCreditAccounts() {
    return this.creditAccounts;
  }

  /**
  *
  *
  * @return
  */
  public ArrayList<CreditCard> getCreditCards() {
    return this.creditCards;
  }
}
