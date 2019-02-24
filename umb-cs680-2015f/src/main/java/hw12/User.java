//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;

/**
* This class defines the concept of user for the program.
*
* @author Pejman Ghorbanzade
* @see Cli
*/
public final class User {
  /**
  * Each User instance has a username.
  */
  private static ArrayList<String> usernames = new ArrayList<String>();
  private final String username;

  /**
  * Construcor of a user instance.
  *
  * @param username with which the user is identified. it is client's
  *        responsibility to ensure no two userk
  * @throws UnsupportedOperationException in case username already exists
  */
  public User(String username) throws UnsupportedOperationException {
    if (usernames.contains(username)) {
      String message = String.format("user '%s' already exists", username);
      throw new UnsupportedOperationException(message);
    } else {
      this.username = username;
    }
  }

  /**
  * This accessor method returns the username of the User instance.
  *
  * @return the username of the user instance
  */
  public String getUsername() {
    return this.username;
  }
}
