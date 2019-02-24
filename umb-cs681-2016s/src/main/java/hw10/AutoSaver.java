//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw10;

/**
 * This class defines an autosaver as an implementation of the runnable
 * interface.
 *
 * @author Pejman Ghorbanzade
 * @see RunnableAgent
 * @see File
 */
public class AutoSaver extends RunnableAgent {

  /**
   * The type of the runnable agent is used to print which agent is acting
   * on the file.
   */ 
  private static final String name = "autosaver";

  /**
   * The constructor takes the file to be saved and passes it to the
   * super class.
   *
   * @param file the file to be saved
   */
  public AutoSaver(File file) {
    super(file);
  }

  /**
   * This method specifies what a thread should do if it is passed
   * an autosaver object.
   */
  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      this.getFile().save(AutoSaver.name);
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
