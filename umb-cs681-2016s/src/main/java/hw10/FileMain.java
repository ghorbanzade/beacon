//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw10;

/**
 * This class demonstrates how editing and saving a file by multiple
 * threads are performed in a thread safe manner.
 *
 * @author Pejman Ghorbanzade
 * @see AutoSaver
 * @see Editor
 * @see File
 */
public final class FileMain {

  /**
   * The main method of this program defines two editor and autosaver threads
   * and starts them. The editor sequentially changes and saves the file every
   * two hundred milliseconds. The autosaver saves the file every two hundred
   * milliseconds.
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
