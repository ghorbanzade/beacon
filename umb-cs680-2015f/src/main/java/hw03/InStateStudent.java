//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw03;

/**
* This class defines international student status and their defined tuition
* as specified in problem statement.
*
* @author Pejman Ghorbanzade
* @see StudentStatus
* @see Student
*/
public class InStateStudent extends StudentStatus {
  /**
  * For simplicity, we assume the tuition fee for all in-state students
  * is fixed on 1000.
  */
  private static final float TUITION = 1000;

  /**
  * Given the fixed tuition fee, this constructor makes sure that all
  * instances of out-state students will have the same tuition without the
  * need to ask the tuition from client every time.
  */
  public InStateStudent() {
    super(TUITION);
  }
}
