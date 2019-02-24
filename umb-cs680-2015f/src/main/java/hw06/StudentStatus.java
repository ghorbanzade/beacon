//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw06;

/**
* This enumeration class provides possible status for college students.
*
* @author Pejman Ghorbanzade
* @see    Student
*/
public enum StudentStatus {
  /**
  * Students are either considered as resident of the state, resident in
  * another state, or resident in a foreign country.
  */
  INSTATE ("instate"),
  OUTSTATE ("outstate"),
  INTL ("international");

  /**
  * Each enum value will have a string representation. This attribute will
  * be used as part of keys to application configuration file.
  */
  private final String name;

  /**
  * This private constructor will initialize name attribute for an enumeration
  * with its string literal value.
  *
  * @param name string literal of the status enumeration
  */
  private StudentStatus(String name) {
    this.name = name;
  }

  /**
  * This method will lookup the required tuition for a student based on his
  * status from application configuration file.
  *
  * @return the tuition a student has to pay based on his status
  */
  public float getTuition() {
    String str = StudentConfig.getInstance().get("tuition.%s", this.name);
    return str.isEmpty() ? 0 : Float.parseFloat(str);
  }
}
