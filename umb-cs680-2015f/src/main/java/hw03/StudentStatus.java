//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw03;

/**
* This abstract class will define the parent class of all possible Student
* status.
*
* @see Student
* @see InStateStudent
* @see OutStateStudent
* @see IntlStudent
*/
public class StudentStatus {
  /**
  * Regardless of their status, all students are supposed to pay a tuition fee.
  */
  private final float tuition;

  /**
  * This constructor gets the required tuition fee from constructors of the
  * subclasses and sets the tuition attribute for the status instance.
  *
  * @param tuition the tuition fee specified for particular student status
  */
  protected StudentStatus(float tuition) {
    this.tuition = tuition;
  }

  /**
  * This method will provide access to the amount of tuition the student has
  * to pay according to his specified student status.
  *
  * @return the amount of tuition that should be paid
  */
  public float getTuition() {
    return this.tuition;
  }
}
