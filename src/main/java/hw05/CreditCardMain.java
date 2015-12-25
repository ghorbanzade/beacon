//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

public final class CreditCardMain {
  public static void main(String[] args) {
    try {
      Customer customer = new Customer("customer-name");
      Bank bank = new Bank("bank-name");
      customer.newBasicAccount(bank, BasicAccountType.CHECKING);
      customer.newBasicAccount(bank, BasicAccountType.SAVING);
      BankAccount account1 = customer.getBasicAccounts().get(0);
      BankAccount account2 = customer.getBasicAccounts().get(1);
      display(customer.getBalance(account1));
      display(customer.getBalance(account2));
      customer.deposit(account1, 1000);
      customer.deposit(account2, 1000);
      customer.withdraw(account2, 500);
      display(customer.getBalance(account1));
      display(customer.getBalance(account2));
      customer.newCreditAccount(bank, CreditAccountType.REWARDS);
      customer.newCreditAccount(bank, CreditAccountType.TRAVEL);
      CreditAccount account3 = customer.getCreditAccounts().get(0);
      CreditAccount account4 = customer.getCreditAccounts().get(1);
      display(customer.getBalance(account3));
      display(customer.getBalance(account4));
      customer.newCreditCard(account3);
      CreditCard card1 = customer.getCreditCards().get(0);
      CreditCard card2 = customer.getCreditCards().get(1);
      display(customer.getCreditLimit(card1));
      display(customer.getCreditLimit(card2));
      customer.setCreditLimit(card1, 300);
      customer.purchase(card1, 100);
      display(customer.getRewards(account3));
      customer.redeem(account3, customer.getRewards(account3));
      display(customer.getBalance(account3));
      customer.transfer(account1, account3, 200);
      display(customer.getBalance(account1));
      display(customer.getBalance(account3));
    } catch (UnsupportedOperationException e) {
      display(e.getMessage());
    }
  }

  private void display(Object object) {
    System.out.print(object.toString());
  }

  private CreditCardMain() {
  }
}
