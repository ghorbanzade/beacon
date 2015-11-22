//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw03;

import java.util.ArrayList;

/**
* This class provides a demonstration of how the proposed design can be used
* by instaniation three students with different status and printing their
* tuition.
*
* @author Pejman Ghorbanzade
* @see Student
* @see StudentStatus
*/
public class StudentMain {
  /**
  * The main method instantiates three students with different status
  * and prints tuition for each one.
  *
  * @param args command line arguments to be given to the program
  */
  public static void main(String[] args) {
    ArrayList<Student> students = new ArrayList<Student>();
    students.add(new Student("Jack", new InStateStudent()));
    students.add(new Student("John", new OutStateStudent()));
    students.add(new Student("Joe", new IntlStudent()));
    for (Student student: students) {
      System.out.printf("Tuition for %s: %.2f%n", student.getName(), student.getTuition());
    }
  }
}
