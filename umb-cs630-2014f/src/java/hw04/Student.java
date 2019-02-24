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
class Student {

  /**
   *
   */
  private String name;
  private int id;

  /**
   *
   */
  public Student() {
    setName("");
    setId(0);
  }

  /**
   *
   */
  public Student(int id, String name) {
    setName(name);
    setId(id);
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
  public int getId() {
    return id;
  }

  /**
   *
   */
  public void setId(int id) {
    this.id = id;
  }

}
