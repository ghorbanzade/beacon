//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw10;

/**
 * This class defines an editor as an implementation of the runnable
 * interface.
 *
 * @author Pejman Ghorbanzade
 * @see RunnableAgent
 * @see File
 */
public class Editor extends RunnableAgent {

  /**
   * The type of the runnable agent is used to print which agent is acting
   * on the file.
   */
  private static final String name = "editor";

  /**
   * The constructor takes the file to be acted upon and passes it to the
   * super class.
   *
   * @param file the file to be changed or saved
   */
  public Editor(File file) {
    super(file);
  }

  /**
   * This method specifies what a thread should do if it is passed
   * an editor object.
   */
  @Override
  public void run() {
    for (int i = 0; i < 20; i++) {
      if (i % 2 == 0) {
        this.getFile().change(Editor.name);
      } else {
        this.getFile().save(Editor.name);
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
