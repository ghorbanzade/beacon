/**
 * CS630: Database Management Systems
 * Copyright 2014 Pejman Ghorbanzade <pejman@ghorbanzade.com>
 * More info: https://github.com/ghorbanzade/beacon
 */

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
class Course {

  /**
   *
   */
  private int id;
  private String name;
  private int credit;

  /**
   *
   */
  public Course() {
    setId(0);
    setName("");
    setCredit(0);
  }

  /**
   *
   */
  public Course(int id, String name, int credit) {
    setId(id);
    setName(name);
    setCredit(credit);
  }

  /**
   *
   */
  public int getId() {
    return id;
  }

  /**
   *
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   *
   */
  public String getName() {
    return name;
  }

  /**
   *
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   *
   */
  public int getCredit() {
    return credit;
  }

  /**
   *
   */
  public void setCredit(int credit) {
    this.credit = credit;
  }

}
