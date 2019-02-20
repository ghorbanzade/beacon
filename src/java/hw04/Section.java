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
class Section {

  /**
   *
   */
  private char sign;
  private String name;
  private String desc;

  /**
   *
   */
  public Section(char sign, String name, String desc) {
    setSign(sign);
    setName(name);
    setDesc(desc);
  }

  /**
   *
   */
  public char getSign() {
    return sign;
  }

  /**
   *
   */
  public void setSign(char sign) {
    this.sign = sign;
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
  public String getDesc() {
    return desc;
  }

  /**
   *
   */
  public void setDesc(String desc) {
    this.desc = desc;
  }

}
