//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw03;

/**
* This class defines in-state student status and their defined tuition
* as specified in problem statement.
*
* @author Pejman Ghorbanzade
* @see StudentStatus
* @see Student
*/
public class IntlStudent extends StudentStatus {
  /**
  * For simplicity, we assume the tuition fee for all international students
  * is fixed on 3000.
  */
  private static final float TUITION = 3000;

  /**
  * Given the fixed tuition fee, this constructor makes sure that all
  * instances of out-state students will have the same tuition without the
  * need to ask the tuition from client every time.
  */
  public IntlStudent() {
    super(TUITION);
  }
}
