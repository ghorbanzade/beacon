//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Assigns an id to a client to make it possible to track a client
 * transactions.
 *
 * @author Pejman Ghorbanzade
 * @see BankClient
 */
public final class Identifier {

  private static final int length = 32;
  private static final String pool = "abcdefghijklmnopqrstuvwxyz";
  private static final SecureRandom rnd = new SecureRandom();

  /**
   * An identifier is a random secure string generator to represent id of
   * bank client. Since instantiating a secure random generator is expensive,
   * this design tries to minimize what should be done for instantiating
   * objects of this class.
   */
  public Identifier() {
    // intentionally left blank.
  }

  /**
   * Returns a random string to represent transaction ids.
   *
   * @return a fixed-length string of random characters
   */
  public String get() {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append(pool.charAt(rnd.nextInt(pool.length())));
    }
    return sb.toString();
  }

}
