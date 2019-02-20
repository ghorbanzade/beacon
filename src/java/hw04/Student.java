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
