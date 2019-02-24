/**
 * CS630: Database Management Systems
 * Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
 * More info: https://github.com/ghorbanzade/beacon
 */

import java.util.Scanner;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public class Main {

  /**
   *
   */
  public static final String oracleServer = "dbs2.cs.umb.edu";
  public static final String oracleServerSid = "dbs2";

  /**
   *
   */
  public static void main(String[] args) {
    CPrompt.welcome();
    Student user = new Student();
    Course course = new Course();
    Database dbs2 = new Database();
    Section[] sections = initMenu();

    CPrompt.inquireUsername(dbs2);
    CPrompt.inquirePassword(dbs2);
    dbs2.loadDriver("oracle.jdbc.OracleDriver");
    dbs2.authenticate();

    CPrompt.inquireId(user);
    if (user.getId() != -1) {
      dbs2.checkStudent(user);
    } else {
      CPrompt.show(0,"You have indicated you are a new student.",1);
      CPrompt.inquireName(user);
      dbs2.addStudent(user);
    }

    boolean loop = true;
    while (loop) {
      showMenu(sections);
      CPrompt.show(0,"",1);
      CPrompt.show(0,"You are in the main menu.",1);
      int sectionIndex = inquireSection();
      if (sectionIndex != -1) {
        switch (sectionIndex) {
          case 0:
            dbs2.courseList();
            break;
          case 1:
            CPrompt.inquireId(course);
            dbs2.courseEnroll(user, course);
            break;
          case 2:
            CPrompt.inquireId(course);
            dbs2.courseWithdraw(user, course);
            break;
          case 3:
            dbs2.courseSearch(CPrompt.inquireSearchPhrase());
            break;
          case 4:
            dbs2.courseMy(user);
            break;
          case 5:
            loop = false;
            break;
        }
      }
    }
  }

  /**
   *
   */
  private static int inquireSection() {
    int sectionNumber = -1;
    boolean flag = false;
    Section[] sections = initMenu();
    Scanner input = new Scanner(System.in);
    CPrompt.show(0,"Please input the letter corresponding to your desired operation.",1);
    CPrompt.show(0,"Sign = ",0);
    String signstr = input.next();
    if (signstr.length() == 1) {
      for (int i = 0 ; i < sections.length ; i++ ){
        if (signstr.charAt(0) == sections[i].getSign()) {
          flag = true;
          sectionNumber = i;
        }
      }
      if (!flag) {
        CPrompt.show(0,"Invalid Input.",1);
        CPrompt.show(0,"No corresponding section found.",2);
      }
    } else {
      CPrompt.show(0,"Invalid Input.",1);
      CPrompt.show(0,"Please insert the letter corresponding to your desired operation.",2);
    }
    return sectionNumber;
  }

  /**
   *
   */
  private static Section[] initMenu() {
    Section s1 = new Section('L',"List","Lists All Courses");
    Section s2 = new Section('E',"Enroll","");
    Section s3 = new Section('W',"Withdraw","");
    Section s4 = new Section('S',"Search","");
    Section s5 = new Section('M',"My Classes","");
    Section s6 = new Section('X',"Exit","");
    Section[] sections = {s1,s2,s3,s4,s5,s6};
    return sections;
  }

  /**
   *
   */
  private static void showMenu(Section[] sections) {
    String message;
    CPrompt.show(1,"Option\tSign\tSection",1);
    CPrompt.show(1,"------\t-------\t-------",1);
    for (int i = 0; i < 6; i++) {
      message = (i+1) + "\t" + sections[i].getSign()+ "\t" +sections[i].getName();
      CPrompt.show(1,message,1);
    }
  }

}
