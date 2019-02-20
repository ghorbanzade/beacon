//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class defines a reader as an object that holds a list of possible
 * pizza orders which can be placed by the client.
 *
 * @author Pejman Ghorbanzade
 * @see PizzaReal
 */
public class MenuReader {

  /**
   * A menu keeps all the items listed in a file it is assigned.
   */
  private final String[] menu;

  /**
   * The constructor takes the path to the file containing menu items and
   * loads their name as an array of strings.
   *
   * @param path the path to the file containing filenames
   */
  public MenuReader(String path) {
    this.menu = this.loadMenu(path);
  }

  /**
   * This helper method initializes the menu with items listed in a file
   * with the given path.
   *
   * @param filePath the path to the file containing pizza names
   * @return the list of pizza names included in the assigned file
   */
  private String[] loadMenu(String path) {
    ArrayList<String> list = new ArrayList<String>();
    try (InputStream fis = MenuReader.class.getResourceAsStream(path)) {
      BufferedReader bf = new BufferedReader(
          new InputStreamReader(fis, "UTF-8")
      );
      String line;
      while ((line = bf.readLine()) != null) {
        if (line.isEmpty() || line.trim().charAt(0) == '#') {
          continue;
        }
        list.add(line);
      }
      bf.close();
      fis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] names = new String[list.size()];
    return list.toArray(names);
  }

  /**
   * This method randomly chooses one of the items listed in the menu.
   *
   * @return a randomly chosen pizza name from the menu
   */
  public String get() {
    int index = (int) Math.floor(Math.random() * this.menu.length);
    return this.menu[index];
  }

}
