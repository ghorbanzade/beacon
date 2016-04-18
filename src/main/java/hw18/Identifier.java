//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw18;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * An identifier object is a secure three-digit random generator object
 * which is assigned to clients when they enter the pizza store.
 *
 * @author Pejman Ghorbanzade
 * @see Client
 */
public final class Identifier {

  /**
   * An identifier object uses a secure random generator object to
   * choose three characters from a pool of characters every time
   * the user tries to generate one.
   */
  private static final int length = 3;
  private static final String pool = "0123456789";
  private static final SecureRandom rnd = new SecureRandom();

  /**
   * Constructor for this class does not have to do anything.
   */
  public Identifier() {
    // intentionally left blank.
  }

  /**
   * This method allows user to get a random generated set of characters
   * chosen from a specified pool of characters.
   *
   * @return a random generated substring of a pool
   */
  public String get() {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append(pool.charAt(rnd.nextInt(pool.length())));
    }
    return sb.toString();
  }

}
