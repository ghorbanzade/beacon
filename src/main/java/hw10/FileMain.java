//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see AutoSaver
 * @see Editor
 * @see File
 */
public class FileMain {

  /**
   *
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    File file = new File("sample.txt");
    Editor editor = new Editor(file);
    AutoSaver autosaver = new AutoSaver(file);
    Thread threadEditor = new Thread(editor);
    Thread threadAutoSaver = new Thread(autosaver);
    threadEditor.start();
    threadAutoSaver.start();
  }

  /**
   * This class should not be instantiated.
   */
  private FileMain() {
  }

}
