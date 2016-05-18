//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

/**
 * Defines an operation to perform on a bank account.
 *
 * @author Pejman Ghorbanzade
 * @see BankAccount
 */
public class Transaction {

  private final TransactionType type;
  private float amount;
  private boolean hasAmount = false;

  /**
   * Constructs a balance checking transactin.
   *
   * @param type the type of this transaction
   */
  public Transaction(TransactionType type) {
    this.type = type;
  }

  /**
   * Constructs a withdrawal or deposit transaction.
   *
   * @param type the type of this transaction
   * @param amount the amount to be deducted or added from a bank account
   */
  public Transaction(TransactionType type, float amount) {
    this.type = type;
    this.amount = amount;
    this.hasAmount = true;
  }

  /**
   * Returns a string representation of this transaction to be used
   * for printing and logging purposes.
   *
   * @return a string representation of this transaction
   */
  @Override
  public String toString() {
    if (this.hasAmount) {
      return String.format("%s %.2f", this.type.toString(), this.amount);
    } else {
      return this.type.toString();
    }
  }

  /**
   * Returns whether this transaction is a withdrawal or deposit or balance
   * checking.
   *
   * @return the type of this transaction
   */
  public TransactionType getType() {
    return this.type;
  }

  /**
   * Returns the amount that should be withdrawn from or deposited into
   * a bank account or returns null if the transaction type is balance
   * checking.
   *
   * @return the amount to be added or deducted from a bank account.
   */
  public float getAmount() {
    return this.amount;
  }

}
