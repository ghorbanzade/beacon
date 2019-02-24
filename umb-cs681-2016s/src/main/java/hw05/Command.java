//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw05;

/**
 * This functional interface is implemented by any (anonymous) class that
 * provides a logic for executing a specific spaceship command.
 *
 * @author Pejman Ghorbanzade
 * @see InputHandler
 */
@FunctionalInterface
public interface Command {
  /**
   * This method executes a command for a given spaceship.
   *
   * @param spaceship the spaceship for which the command is issued
   */
  public abstract void execute(Spaceship spaceship);
}
