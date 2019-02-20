//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw10;

/**
 * This class is the parent class of any class that is going to act on
 * a file object.
 *
 * @author Pejman Ghorbanzade
 * @see AutoSaver
 * @see Editor
 */
public abstract class RunnableAgent implements Runnable {

  /**
   * Any runnable object takes account of the file is assigned to affect.
   */
  private final File file;

  /**
   * The constructor records the file to be acted upon.
   *
   * @param file the file to be either saved or changed.
   */
  public RunnableAgent(File file) {
    this.file = file;
  }

  /**
   * All classes extending this class must implement how threads should
   * execute the class.
   */
  public abstract void run();

  /**
   * This method provides access to the file that the runnable agent is given.
   *
   * @return the file that the runnable agent is acting upon
   */
  public File getFile() {
    return this.file;
  }

}
