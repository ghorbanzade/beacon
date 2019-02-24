//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
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
public class Student {
  /**
  * Each student has a name and a status which is one of the possibilities
  * defined in StudentStatus enum class.
  */
  private final String name;
  private final StudentStatus status;

  /*
  * The constructor will take name and status of a student. The tuition of
  * the student is initialized immediately according to the configuraiton
  * provided in student.properties file.
  */
  protected Student(String name, StudentStatus status) {
    this.name = name;
    this.status = status;
  }

  /*
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
