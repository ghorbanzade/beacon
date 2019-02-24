/**
 * CS630: Database Management Systems
 * Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
 * More info: https://github.com/ghorbanzade/beacon
 */

import java.io.Console;
import java.util.Scanner;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
class CPrompt {

  /**
   *
   */
  private static int indent;
  private static int lineSpace;

  /**
   *
   */
  public CPrompt() {
      setIndent(0);
      setLineSpace(1);
  }

  /**
   *
   */
  public static void show(String message) {
    int i;
    for ( i = 0 ; i < getIndent() ; i++ ) {
      System.out.printf("\t");
    }
    System.out.printf(message);
    for ( i = 0 ; i < getLineSpace() ; i++ ) {
      System.out.printf("\n");
    }
  }

  /**
   *
   */
  public static void show(int indent, String message, int lineSpace) {
    int i;
    for ( i = 0 ; i < indent ; i++ ) {
      System.out.printf("\t");
    }
    System.out.printf(message);
    for ( i = 0 ; i < lineSpace ; i++ ) {
      System.out.printf("\n");
    }
  }

  /**
   *
   */
  public static void welcome() {
    String message;
    message = "\n\n\n\n"
        + "--------------------------------------\n"
        + "Assignment 4, Question 2\n"
        + "CS630 Database Management Systems\n"
        + "Department of Computer Science\n"
        + "University of Massachuetts Boston\n"
        + "Pejman Ghorbanzade <pejman@cs.umb.edu>\n"
        + "--------------------------------------\n";
    show(message);
  }

  /**
   *
   */
  public static void inquireId(Course course) {
    int cId = 0;
    boolean loop = true;
    Scanner input = new Scanner(System.in);
    while (loop) {
      CPrompt.show(0,"Course ID = ",0);
      String courseIdStr = input.next();
      try {
        cId = Integer.parseInt(courseIdStr);
        if (cId != 0) {
          loop = false;
        }
      } catch (Exception e) {
        CPrompt.show(0,"Invalid input.",1);
        CPrompt.show(0,"Course ID is always a number.",2);
      }
    }
    course.setId(cId);
  }

  /**
   *
   */
  public static void inquireId(Student student) {
    boolean loop = true;
    Scanner input = new Scanner(System.in);
    while (loop) {
      CPrompt.show(0,"Please enter your Student ID.",1);
      CPrompt.show(0,"Enter -1 if you are new student.",1);
      CPrompt.show(0,"Student ID = ",0);
      String studIDstr = input.next();
      try {
        int studID = Integer.parseInt(studIDstr);
        if (studID != 0) {
          student.setId(studID);
          loop = false;
        }
      } catch (Exception e) {
        CPrompt.show(0,"Invalid input.",1);
        CPrompt.show(0,"Student ID is always a number.",2);
      }
    }
  }

  /**
   *
   */
  public static void inquireName(Student student) {
    CPrompt.show(0,"What is your name?",1);
    Scanner input = new Scanner(System.in);
    CPrompt.show(0,"Name = ",0);
    student.setName(input.next());
  }

  /**
   *
   */
  public static void inquireUsername(Database db) {
    Scanner input = new Scanner(System.in);
    CPrompt.show(0,"Username = ",0);
    db.setAuthUser(input.next());
  }

  /**
   *
   */
  public static void inquirePassword(Database db) {
    Console console = System.console();
    CPrompt.show(0,"Password = ",0);
    db.setAuthPass(new String(console.readPassword()));
  }

  /**
   *
   */
  public static String inquireSearchPhrase() {
    Scanner input = new Scanner(System.in);
    CPrompt.show(0,"Course Phrase = ",0);
    String searchPhrase = input.next();
    return searchPhrase;
  }

  /**
   *
   */
  public static int getIndent() {
    return indent;
  }

  /**
   *
   */
  public static void setIndent(int input) {
    indent = input;
  }

  /**
   *
   */
  public static int getLineSpace() {
    return lineSpace;
  }

  /**
   *
   */
  public static void setLineSpace(int input) {
    lineSpace = input;
  }

}
