//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

import java.util.ArrayList;

public class Customer extends Client {

  ArrayList<BasicAccount> basicAccounts = new ArrayList<BasicAccount>();
  ArrayList<CreditAccount> creditAccounts = new ArrayList<CreditAccount>();
  ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();

  public Customer(String name) {
    super(name);
  }

  public void newBasicAccount(Bank bank, BasicAccountType type) {
    this.basicAccounts.add(new BasicAccount(bank, this, type));
  }

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

  public float getBalance(BankAccount account)
      throws UnsupportedOperationException {
    return account.getBalance(this);
  }

  public void deposit(BankAccount account, float amount) {
    account.deposit(this, amount);
  }

  public void withdraw(BankAccount account, float amout)
      throws UnsupportedOperationException {
    
  }

  public void transfer(BankAccount src, BankAccount dst,
      float amount) throws UnsupportedOperationException {
    src.withdraw(this, amount);
    dst.deposit(this, amount);
  }

  public void purchase(CreditCard card, float amount)
      throws UnsupportedOperationException {
    card.purchase(this, amount);
  }

  public float getRewards(CreditCard card)
      throws UnsupportedOperationException {
    return card.getRewards(this);
  }

  public void redeem(CreditCard card, float amount)
      throws UnsupportedOperationException {
    card.redeem(this, amount);
  }

  public float getCreditLimit(CreditAccount account)
      throws UnsupportedOperationException {
    return account.getCreditLimit(this);
  }

  public float getCreditLimit(CreditCard card)
      throws UnsupportedOperationException {
    return card.getCreditLimit(this);
  }

  public void setCreditLimit(CreditCard card, float limit)
      throws UnsupportedOperationException {
    card.setCreditLimit(this, limit);
  }

  public ArrayList<BasicAccount> getBasicAccounts() {
    return this.basicAccounts;
  }

  public ArrayList<CreditAccount> getCreditAccounts() {
    return this.creditAccounts;
  }

  public ArrayList<CreditCard> getCreditCards() {
    return this.creditCards;
  }
}
