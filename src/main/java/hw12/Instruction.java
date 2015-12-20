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
* The instruction class interprets each one-liner user command
* as an instruction object. Passing this object to the cli would
* allow a more organized processing of the user input.
*
* @author Pejman Ghorbanzade
* @see Cli
*/
public final class Instruction {
  /**
  * Each instance of instruction contains the command name, a list
  * of arguments and a list of options. The object also includes
  * the date the object is instantiated.
  */
  private Date date;
  private String name;
  private ArrayList<String> arguments = new ArrayList<String>();
  private ArrayList<String> options = new ArrayList<String>();

  /**
  * This constructor takes the one-liner command given by the user
  * and creates the instruction instance for it.
  *
  * @param string the one-liner user-input as is
  */
  public Instruction(String string) {
    this.date = new Date();
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
  }

  /**
  * This accessor method returns the date the instruction instance
  * has been created.
  *
  * @return the date the instruction has been issued
  */
  public Date getDate() {
    return this.date;
  }

  /**
  * This accessor method returns the name of the command wrapped
  * in the instruction instance.
  *
  * @return the name of the command
  */
  public String getName() {
    return this.name;
  }

  /**
  * This accessor method returns the arguments passed to the command.
  *
  * @return the list of arguments of the command
  */
  public ArrayList<String> getArguments() {
    return this.arguments;
  }

  /**
  * This accessor method returns the options the command has
  * been invoked with.
  *
  * @return the list of options of the command
  */
  public ArrayList<String> getOptions() {
    return this.options;
  }

  /**
  * This method defines how the instruction object should be
  * printed. This method is used in printing history of the
  * cli instance.
  *
  * @return the string representation of an instruction instance
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
