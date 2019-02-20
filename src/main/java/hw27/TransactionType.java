//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

/**
 * Defines the type of a transaction which is either withdrawal, deposit or
 * a simple balance checking.
 *
 * @author Pejman Ghorbanzade
 * @see Transaction
 */
public enum TransactionType {

  BALANCE ("BALANCE"),
  DEPOSIT ("DEPOSIT"),
  WITHDRAW ("WITHDRAW"),
  QUIT ("QUIT");

  private final String name;

  /**
   * For each enumeration item of this class, assigned a string literal
   * value to be used for printing or logging purporses.
   *
   * @param name the string literal assigned to this type
   */
  private TransactionType(String name) {
    this.name = name;
  }

  /**
   * Returns a string representation of this transaction type.
   *
   * @return a string representation of this transaction type
   */
  @Override
  public String toString() {
    return this.name;
  }

}
