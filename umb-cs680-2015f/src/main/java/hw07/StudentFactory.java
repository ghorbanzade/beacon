//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw07;

/**
* This class defines a student as an object with a name and a status. The later
* can be one of enum values provided in StudentStatus class.
*
* @author Pejman Ghorbanzade
* @see Student
* @see StudentStatus
*/
public final class StudentFactory {
  /**
  * This static factory method will return a new in-state student with specified
  * name.
  *
  * @param name of the new in-state student
  * @return a student object with in-state status
  */
  public static Student createInStateStudent(String name) {
    return new Student(name, new InStateStudent());
  }

  /**
  * This static factory method will return a new out-state student with
  * specified name.
  *
  * @param name of the new out-state student
  * @return a student object with out-state status
  */
  public static Student createOutStateStudent(String name) {
    return new Student(name, new OutStateStudent());
  }

  /**
  * This static factory method will return a new international student
  * with the specified name.
  *
  * @param name name of the new international student
  * @return a student object with international status
  */
  public static Student createIntlStudent(String name) {
    return new Student(name, new IntlStudent());
  }

  /**
  * This class must not be instantiated.
  */
  private StudentFactory() {
  }
}
