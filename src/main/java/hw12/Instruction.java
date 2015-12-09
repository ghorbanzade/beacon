//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class Instruction {
  /**
  *
  */
  private static int number = 1;
  private Date date;
  private int identifier;
  private String name;
  private ArrayList<String> arguments = new ArrayList<String>();
  private ArrayList<String> options = new ArrayList<String>();

  /**
  *
  *
  * @param string
  */
  public Instruction(String string) {
    this.date = new Date();
    this.identifier = number;
    String[] strings = string.split("\\s+");
    ArrayList<String> words = new ArrayList<String>(Arrays.asList(strings));
    this.name = words.remove(0);
    for (String word: words) {
      if (word.charAt(0) == '-') {
        this.options.add(word.substring(1));
      } else {
        this.arguments.add(word);
      }
    }
    number++;
  }

  /**
  *
  *
  * @return
  */
  public Date getDate() {
    return this.date;
  }

  /**
  *
  *
  * @return
  */
  public int getId() {
    return this.identifier;
  }

  /**
  *
  *
  * @return
  */
  public String getName() {
    return this.name;
  }

  /**
  *
  *
  * @return
  */
  public ArrayList<String> getArguments() {
    return this.arguments;
  }

  /**
  *
  *
  * @return
  */
  public ArrayList<String> getOptions() {
    return this.options;
  }

  /**
  *
  *
  * @return
  */
  public String toString() {
    StringBuilder sb = new StringBuilder(this.name);
    for (String argument: this.arguments) {
      sb.append(String.format(" %s", argument));
    }
    for (String option: this.options) {
      sb.append(String.format(" -%s", option));
    }
    return sb.toString();
  }
}
