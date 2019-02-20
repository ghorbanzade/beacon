//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
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
