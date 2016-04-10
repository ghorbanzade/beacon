//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

/**
 *
 */
public class Transaction {

  /**
   *
   */
  private final TransactionType type;
  private float amount;
  private boolean hasAmount = false;

  /**
   *
   *
   * @param type
   */
  public Transaction(TransactionType type) {
    this.type = type;
  }

  /**
   *
   *
   * @param type
   * @param amount
   */
  public Transaction(TransactionType type, float amount) {
    this.type = type;
    this.amount = amount;
    this.hasAmount = true;
  }

  /**
   *
   *
   * @return
   */
  @Override
  public String toString() {
    if (!this.hasAmount) {
      return this.type.toString();
    } else {
      return String.format("%s %.2f", this.type.toString(), this.amount);
    }
  }

  /**
   *
   *
   * @return
   */
  public TransactionType getType() {
    return this.type;
  }

  /**
   *
   *
   * @return
   */
  public float getAmount() {
    return this.amount;
  }

}
