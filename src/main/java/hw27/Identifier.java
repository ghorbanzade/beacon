//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.security.SecureRandom;
import java.math.BigInteger;

/**
 *
 */
public final class Identifier {

  /**
   *
   */
  private static final int length = 32;
  private static final String pool = "abcdefghijklmnopqrstuvwxyz";
  private static final SecureRandom rnd = new SecureRandom();

  /**
   *
   */
  public Identifier() {
    // intentionally left blank.
  }

  /**
   *
   */
  public String get() {
    StringBuilder sb = new StringBuilder(length);
    for(int i = 0; i < length; i++) {
      sb.append(pool.charAt(rnd.nextInt(pool.length())));
    }
    return sb.toString();
  }

}
