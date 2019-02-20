//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw05;

/**
 * This enum class provides the set of control keys as enum values.
 *
 * @author Pejman Ghorbanzade
 * @see InputHandler
 */
public enum Key {

  /**
   * Supported control keys are moving the spaceship to left, moving the
   * spaceship to right, firing toward the enemy target and exiting the program.
   * There is also a key that represents the state when no key is pressed.
   */
  LEFT ("left"),
  NONE ("none"),
  RIGHT ("right"),
  FIRE ("fire"),
  QUIT ("quit");

  /**
   * Each key has a name associated with it.
   */
  private final String name;

  /**
   * The constructor simply associates the enum value with its name.
   *
   * @param name the name associated with the new key
   */
  private Key(String name) {
    this.name = name;
  }

  /**
   * This getter method allows access to the name of a key.
   *
   * @return the name associated with a key
   */
  public String getName() {
    return this.name;
  }
}
