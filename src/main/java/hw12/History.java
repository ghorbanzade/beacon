//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.NumberFormatException;
import java.util.ArrayList;

/**
* This class defines a fixed-size dynamic data structure to record
* a fixed number of most recent instructions issued by the user.
*
* @author Pejman Ghorbanzade
* @see Cli
*/
public final class History {
  /**
  * Each history instance will have a list of user-issued instructions.
  * Size of this list will be no more than the limit specified for the
  * instance.
  */
  private int limit;
  private ArrayList<Instruction> instructions;

  /**
  * The constructor for this class will create a list of instructions
  * of the specified size.
  *
  * @param size the initial size of the list for storing instructions.
  */
  public History(int size) {
    this.limit = size;
    this.instructions = new ArrayList<Instruction>(this.limit);
  }

  /**
  * This method will add an instruction to the history list, while
  * ensuring the number of stored instructions does not exceed the
  * size of the history.
  *
  * @param instruction the instruction to be added to the list.
  */
  public void add(Instruction instruction) {
    if (instructions.size() == this.limit) {
      this.instructions.remove(0);
    }
    this.instructions.add(instruction);
  }

  /**
  * This mutator method will update the size of the history structure.
  * This method may be used in setCommand to allow recording different
  * number of instructions.
  *
  * @param limit the number of instructions to be stored in the history
  *        instance.
  */
  public void updateSize(int limit) {
    this.limit = limit;
    this.instructions.ensureCapacity(limit);
  }

  /**
  * This accessor method will return the list of stored instructions.
  * This method may be used to print the history of user-issued commands
  * as in history command.
  *
  * @return the list of stored instructions
  */
  public ArrayList<Instruction> getInstructions() {
    return this.instructions;
  }
}
