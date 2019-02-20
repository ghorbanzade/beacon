//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw05;

/**
 * This class defines a spaceship as the object on the board whose control
 * is given to the user to destroy enemy targets.
 *
 * @author Pejman Ghorbanzade
 * @see Position
 */
public class Spaceship {

  /**
   * A spaceship object has a position initialized at bottom middle
   * of the board.
   */
  private final Position pos = new Position(Board.WIDTH / 2, 0);

  /**
   * This method specifies how a spaceship should move across the board.
   *
   * @param posx number of tiles spaceship moves on x-axis
   * @param posy number of tiles spaceship moves on y-axis
   */
  public void move(int posx, int posy) {
    this.pos.move(posx, posy);
    System.out.printf("Position: %s%n", this.pos);
  }

  /**
   * This method specifies how a spaceship should fire at targets.
   */
  public void fire() {
    System.out.printf("Shooting bullets!%n");
  }

}
