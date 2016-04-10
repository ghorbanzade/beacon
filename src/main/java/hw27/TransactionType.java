//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see Transaction
 */
public enum TransactionType {

  /**
   *
   */
  BALANCE ("BALANCE"),
  DEPOSIT ("DEPOSIT"),
  WITHDRAW ("WITHDRAW"),
  QUIT ("QUIT");

  /**
   *
   */
  private final String name;

  /**
   *
   *
   * @param name
   */
  private TransactionType(String name) {
    this.name = name;
  }

  /**
   *
   *
   * @return
   */
  @Override
  public String toString() {
    return this.name;
  }

}
