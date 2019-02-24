//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw05;

/**
 * This class simply demonstrates the design using lambda expressions
 * and command design strategy.
 *
 * @author Pejman Ghorbanzade
 * @see InputHandler
 * @see Spaceship
 */
public final class SpaceMain {

  /**
   * The main method of this program creates a spaceship object, specifies
   * how it implements supported movements and firing commands and allows
   * user to control the spaceship by entering input keys.
   *
   * <p>The program terminates when user enters <i>q</i> as input.
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    Spaceship spaceship = new Spaceship();
    InputHandler ih = new InputHandler(spaceship,
        (Spaceship sp)-> sp.move(-1, 0),
        (Spaceship sp)-> sp.move(1, 0),
        (Spaceship sp)-> sp.fire()
        );
    showManual();
    while (true) {
      try {
        ih.handleInput();
      } catch (SpaceshipException e) {
        System.out.printf("%s%n", e.getMessage());
        break;
      }
    }
  }

  /**
   * This method simply prints a header on top of the board giving user
   * basic instructions on how to interact with the program.
   */
  private static void showManual() {
    System.out.printf("f: fire, r: right, l: left, q: quit%n");
    System.out.printf("-----------------------------------%n");
  }

  /**
   * This class must not be instantiated.
   */
  private SpaceMain() {
  }

}
