//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw06;

/**
* This class defines a student as an object with a name and a status. The later
* can be one of enum values provided in StudentStatus class.
*
* @author Pejman Ghorbanzade
* @see Student
* @see StudentStatus
*/
public class Student {
  /**
  * Each student has a name and a status which is one of the possibilities
  * defined in StudentStatus enum class.
  */
  private final String name;
  private final StudentStatus status;

  /**
  * This private constructor will take name and status of the student to
  * initialize them during instantiation. This constructor will be called
  * by static factory methods that are provided to the client.
  *
  * @param name name of the student
  * @param status status of the student
  */
  private Student(String name, StudentStatus status) {
    this.name = name;
    this.status = status;
  }

  /**
  * This static factory method will return a new in-state student with specified
  * name.
  *
  * @param name of the new in-state student.
  */
  public static Student createInStateStudent(String name) {
    return new Student(name, StudentStatus.INSTATE);
  }

  /**
  * This static factory method will return a new out-state student with
  * specified name.
  *
  * @param name of the new out-state student.
  */
  public static Student createOutStateStudent(String name) {
    return new Student(name, StudentStatus.OUTSTATE);
  }

  /**
  * This static factory method will return a new international student
  * with the specified name.
  *
  * @param name name of the new international student
  */
  public static Student createIntlStudent(String name) {
    return new Student(name, StudentStatus.INTL);
  }

  /**
  * This method simply returns the name of the student.
  *
  * @return name of the student instance
  */
  public String getName() {
    return this.name;
  }

  /*
  * This accessor method will return tuition of the student by calling
  * getTuition method developed in StudentStatus class.
  *
  * @return tuition the student has to pay based on his status
  */
  public float getTuition() {
    return this.status.getTuition();
  }
}
