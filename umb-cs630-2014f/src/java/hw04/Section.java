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
