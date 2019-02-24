//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw05;

/**
*
*
* @author Pejman Ghorbanzade
*/
public enum BasicAccountType {
  CHECKING("checking"),
  SAVING("saving");

  /**
  *
  */
  private final String name;

  /**
  *
  *
  * @param name
  * @return
  */
  private BasicAccountType(String name) {
    this.name = name;
  }
}
