/**
 * CS630: Database Management Systems
 * Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
 * More info: https://github.com/ghorbanzade/beacon
 */

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
class Database {

  /**
   *
   */
  public static final String oracleServer = "dbs2.cs.umb.edu";
  public static final String oracleServerSid = "dbs2";
  private String authUser;
  private String authPass;
  private Connection conn;

  /**
   *
   */
  public void loadDriver(String driver) {
    try {
      Class.forName(driver);
      CPrompt.show(0,"Driver loaded successfulyy.",1);
    } catch (Exception e) {
      CPrompt.show(0,"Could not load driver.",1);
    }
  }

  /**
   *
   */
  public void authenticate() {
    String connString = "jdbc:oracle:thin:@" + oracleServer + ":1521:" + oracleServerSid;
    CPrompt.show(0,"Authenticating...",1);
    try {
      conn = DriverManager.getConnection(connString, getAuthUser(), authPass);
      CPrompt.show(0,"Authentication Successful.",2);
    }
    catch (SQLException e) {
      CPrompt.show(0,"Authentication failed.",1);
      System.exit(1);
    }
  }

  /**
   *
   */
  public int numberOfRows(String tableName) {
    int numRows = -1;
    String sql = "SELECT COUNT(*) FROM " + tableName;
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      ResultSet result = pstmt.executeQuery();
      result.next();
      numRows = result.getInt(1);
    }
    catch (SQLException e) {
      CPrompt.show(0, "Failed to count number of rows in a table.", 1);
      System.exit(1);
    }
    return numRows;
  }

  public void welcomeStudent(Student student) {
    String sql = "SELECT S.sname FROM Students S WHERE S.sid = ?";
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, student.getId());
      ResultSet result = pstmt.executeQuery();
      while (result.next()) {
          student.setName(result.getString(1));
          CPrompt.show(0, "Welcome " + student.getName() + "!", 2);
      }
    } catch (SQLException ex) {
      CPrompt.show(0, "Error in student Identification.", 1);
      System.exit(1);
    }
  }

  /**
   *
   */
  public void checkStudent(Student student) {
    String sql = "SELECT S.sid FROM Students S WHERE S.sid = ?";
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, student.getId());
      int rowsAffected = pstmt.executeUpdate();
      if (rowsAffected == 0) {
        CPrompt.show(0,"No such Student ID exists.",1);
        System.exit(1);
      } else {
        welcomeStudent(student);
      }
    } catch (SQLException ex) {
      CPrompt.show(0,"Failed to verify student registration.",1);
      System.exit(1);
    }
  }

  /**
   *
   */
  public void addStudent(Student student) {
    student.setId(numberOfRows("Students")+1);
    String sql = "INSERT INTO Students (sid, sname) VALUES (?, ?)";
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, student.getId());
      pstmt.setString(2, student.getName());
      int rowsAffected = pstmt.executeUpdate();
      if (rowsAffected == 0) {
        CPrompt.show(0,"Failed to Add Student.",1);
        System.exit(1);
      } else {
        welcomeStudent(student);
      }
    } catch (SQLException ex) {
        CPrompt.show(0,"Failed to add new student.",3);
        System.exit(1);
    }
  }

  /**
   *
   */
  public void courseList() {
    CPrompt.show(0,"Here is the list of all courses.",2);
    CPrompt.setIndent(1);
    CPrompt.setLineSpace(1);
    String sql = "SELECT * FROM Courses";
    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet result = pstmt.executeQuery();
        CPrompt.show("ID\tCredit\tName");
        CPrompt.show("------\t------\t-------");
        while (result.next()) {
            int courseId = result.getInt(1);
            String courseName = result.getString(2);
            int courseCredit = result.getInt(3);
            CPrompt.show(courseId + "\t" + courseCredit + "\t" + courseName);
        }
        CPrompt.show("");
    } catch (SQLException e) {
        CPrompt.show("Failed to get course list.");
        System.exit(1);
    }
    CPrompt.setIndent(0);
  }

  /**
   *
   */
  public boolean courseValid(Course course) {
    String sql = "SELECT C.cid FROM Courses C WHERE C.cid = ?";
    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, course.getId());
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected == 0) {
            CPrompt.show(0,"No such course exists.",2);
            return false;
        }
    } catch (SQLException e) {
        CPrompt.show(0,"Failed to check if course is valid.",3);
        System.exit(1);
    }
    return true;
  }

  /**
   *
   */
  public boolean currentlyEnrolled(Student student, Course course) {
    String sql = "SELECT * FROM Enrolled E WHERE E.sid = ? AND E.cid = ?";
    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,student.getId());
        pstmt.setInt(2,course.getId());
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected == 0) {
            return false;
        }
    } catch (SQLException e) {
        CPrompt.show(0,"Failed to check if student is currently enrolled.",3);
        System.exit(1);
    }
    return true;
  }

  /**
   *
   */
  public void courseEnroll(Student student, Course course) {
    if (courseValid(course)) {
      if (currentlyEnrolled(student,course)) {
          CPrompt.show(0, "You are currently enrolled in this course.", 2);
      } else {
        String sql = "INSERT INTO Enrolled E (sid, cid) VALUES (?,?)";
        try {
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1,student.getId());
          pstmt.setInt(2,course.getId());
          int rowsAffected = pstmt.executeUpdate();
          if (rowsAffected == 0) {
              CPrompt.show(0, "Could not enroll you in this course.", 3);
              System.exit(1);
          } else {
              CPrompt.show(0, "You are now enrolled in this course.", 2);
          }
        } catch (SQLException e) {
          CPrompt.show(0, "Failed to enroll you in this course.", 3);
          System.exit(1);
        }
      }
    }
  }

  /**
   *
   */
  public void courseWithdraw(Student student, Course course) {
    if (courseValid(course)) {
      if (currentlyEnrolled(student, course)) {
        String sql = "DELETE FROM Enrolled E WHERE E.sid = ? AND E.cid = ?";
        try {
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1,student.getId());
          pstmt.setInt(2,course.getId());
          int rowsAffected = pstmt.executeUpdate();
          if (rowsAffected == 0) {
            CPrompt.show(0,"Could not withdraw this course.",3);
            System.exit(1);
          } else {
              CPrompt.show(0,"Course successfully withdrawn.",2);
          }
        } catch (SQLException e) {
          CPrompt.show(0,"Failed to withdraw this course.",3);
          System.exit(1);
        }
      }
      else {
          CPrompt.show(0,"You are not even enrolled in this course.",2);
      }
    }
  }

  /**
   *
   */
  public void courseSearch(String searchPhrase) {
    CPrompt.show(0,"Here is the list of all courses matching specified criteria.",2);
    CPrompt.setIndent(1);
    CPrompt.setLineSpace(1);
    String sql = "SELECT * FROM Courses C WHERE C.cname LIKE ?";
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, searchPhrase);
      ResultSet result = pstmt.executeQuery();
      CPrompt.show("ID\tCredit\tName");
      CPrompt.show("------\t------\t-------");
      while (result.next()) {
          int courseId = result.getInt(1);
          String courseName = result.getString(2);
          int courseCredit = result.getInt(3);
          CPrompt.show(courseId + "\t" + courseCredit + "\t" + courseName);
      }
      CPrompt.show("");
    } catch (SQLException e) {
      CPrompt.show(0, "Failed to search courses.", 1);
      System.exit(1);
    }
    CPrompt.setIndent(0);
  }

  /**
   *
   */
  public void courseMy(Student student) {
    CPrompt.show(0,"Here is the list of courses you are currently enrolled in.",2);
    CPrompt.setIndent(1);
    CPrompt.setLineSpace(1);
    String sql = "SELECT C.cid, C.cname, C.credits FROM Courses C, Students S, "
               + "Enrolled E WHERE E.cid = C.cid AND E.sid = S.sid AND S.sid = ?";
    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,student.getId());
        ResultSet result = pstmt.executeQuery();
        CPrompt.show("ID\tCredit\tName");
        CPrompt.show("------\t------\t-------");
        while (result.next()) {
            int courseId = result.getInt(1);
            String courseName = result.getString(2);
            int courseCredit = result.getInt(3);
            CPrompt.show(courseId + "\t" + courseCredit + "\t" + courseName);
        }
        CPrompt.show("");
    } catch (SQLException e) {
        CPrompt.show("Failed to fetch student courses.");
        System.exit(1);
    }
    CPrompt.setIndent(0);
  }

  /**
   *
   */
  public String getAuthUser() {
    return authUser;
  }

  /**
   *
   */
  public void setAuthUser(String authUser) {
    this.authUser = authUser;
  }

  /**
   *
   */
  public void setAuthPass(String authPass) {
    this.authPass = authPass;
  }

}
