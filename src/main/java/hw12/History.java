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
*
*
* @author Pejman Ghorbanzade
*/
public final class History {
  /**
  *
  */
  private int limit;
  private ArrayList<Instruction> instructions;

  /**
  *
  *
  * @throws NumberFormatException
  */
  public History() throws NumberFormatException {
    String res = CliConfig.getInstance().get("cli.history.size");
    this.limit = Integer.parseInt(res);
    this.instructions = new ArrayList<Instruction>(this.limit);
  }

  /**
  *
  *
  * @param instruction
  */
  public void add(Instruction instruction) {
    if (instructions.size() == this.limit) {
      this.instructions.remove(0);
    }
    this.instructions.add(instruction);
  }

  /**
  *
  *
  * @param limit
  */
  public void updateSize(int limit) {
    this.limit = limit;
    this.instructions.ensureCapacity(limit);
  }

  /**
  *
  *
  * @return
  */
  public ArrayList<Instruction> getInstructions() {
    return this.instructions;
  }
}
