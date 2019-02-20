/**
 * CS630: Database Management Systems
 * Copyleft 2014 Pejman Ghorbanzade <mail@ghorbanzade.com>
 * More info: https://bitbucket.org/ghorbanzade/umb-cs630-2014
 *
 * The author has placed this file in the public domain.
 * He makes no warranty and accepts no liability for this file.
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
