//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw05;

import java.util.Scanner;

/**
 * This class provides how our spaceship should function in the game.
 *
 * @author Pejman Ghorbanzade
 * @see Command
 */
public class InputHandler {

  /**
   * Each input handler object has the spaceship object as its attribute and
   * implements how basic movements and firing operations should function
   * for that spaceship.
   */
  private final Spaceship spaceship;
  private final Command keyLeft;
  private final Command keyRight;
  private final Command keyFire;

  /**
   * An input handler objcet must provide four implementations of the
   * command interface.
   *
   * @param spaceship the object on which commands would have effect
   * @param left the way the object will move to the left
   * @param right the way the object will move to the right
   * @param fire the way the object will fire
   */
  public InputHandler(
      Spaceship spaceship, Command left, Command right, Command fire) {
    this.spaceship = spaceship;
    this.keyLeft = left;
    this.keyRight = right;
    this.keyFire = fire;
  }

  /**
   * This method executes one command for the spaceship per turn.
   *
   * @throws SpaceshipException if user quits the program
   */
  public void handleInput() throws SpaceshipException {
    Key key = getKey();
    switch (key) {
      case LEFT:
        this.keyLeft.execute(this.spaceship);
        break;
      case RIGHT:
        this.keyRight.execute(this.spaceship);
        break;
      case FIRE:
        this.keyFire.execute(this.spaceship);
        break;
      case QUIT:
        throw new SpaceshipException("Program terminated per user request");
      default:
        break;
    }
  }

  /**
   * This class gets the key enum value from user. The key will be passed to
   * handleInput method to execute the command associated with it.
   *
   * @return the enum value corresponding to the user input
   */
  private Key getKey() {
    char ch;
    String input;
    Scanner in = new Scanner(System.in, "UTF-8");
    do {
      input = in.nextLine();
    } while (input.isEmpty());
    ch = input.charAt(0);
    switch (ch) {
      case 'r': return Key.RIGHT;
      case 'l': return Key.LEFT;
      case 'f': return Key.FIRE;
      case 'q': return Key.QUIT;
      default: return Key.NONE;
    }
  }

}
