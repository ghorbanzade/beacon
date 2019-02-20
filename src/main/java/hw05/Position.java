//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw05;

import java.awt.Point;

/**
 * This class specifies a position object as an attribute for any piece on
 * the game board.
 *
 * @author Pejman Ghorbanzade
 * @see Spaceship
 */
public class Position {

  /**
   * a position object has a point that describes its location on the
   * game board.
   */
  private Point pos;

  /**
   * Constructing a position object requires specifying its location on
   * the board.
   *
   * @param posx location in x-axis
   * @param posy location in y-axis
   */
  public Position(int posx, int posy) {
    if (posx > Board.WIDTH || posy > Board.HEIGHT || posx < 0 || posy < 0) {
      this.pos = new Point();
    }
    this.pos = new Point(posx, posy);
  }

  /**
   * Position of an object can be changed by invoking the move method with
   * parameters representing displacements in x and y axes. 
   *
   * @param dx displacement in x-axis
   * @param dy displacement in y-axis
   */
  public void move(int dx, int dy) {
    int posx = (int) this.pos.getX() + dx;
    int posy = (int) this.pos.getY() + dy;
    posx = Math.max(0, Math.min(posx, Board.WIDTH));
    posy = Math.max(0, Math.min(posy, Board.HEIGHT));
    this.pos.setLocation(posx, posy);
  }

  /**
   * This method simplifies printing information about position of an object.
   *
   * @return a string representation of the position object
   */
  public String toString() {
    return this.pos.toString();
  }

}
