//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class defines a file name reader as an object that generates
 * random filenames based on a resource file that contains multiple
 * filenames.
 *
 * @author Pejman Ghorbanzade
 * @see FileSystem
 */
public class FileNameReader {

  /**
   * A filename reader keeps track of an array of filenames from which a
   * filename can be chosen.
   */
  private final String[] names;

  /**
   * A filename reader object takes the path to a resource file and uses
   * the names inside that file as a list of possible filenames to choose
   * names from.
   * 
   * <p>Upon construction, a filename reader object loads all possible
   * filenames for faster access.
   *
   * @param path path to the file containing a list of possible filenames
   */
  public FileNameReader(String path) {
    this.names = this.loadNames(path);
  }

  /**
   * This method takes the path of a resource file and loads its content
   * as a list of possible filenames to be used for generating random
   * filenames for different file system elements.
   *
   * @param path the path to the file whose content should be loaded
   * @return an array of filenames to choose a filename from
   */
  private String[] loadNames(String path) {
    ArrayList<String> list = new ArrayList<String>();
    try (InputStream fis = FileNameReader.class.getResourceAsStream(path)) {
      BufferedReader bf = new BufferedReader(
          new InputStreamReader(fis, "UTF-8")
      );
      String line;
      while ((line = bf.readLine()) != null) {
        if (line.trim().charAt(0) == '#') {
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
   * This method generates a random file name to be assigned to a file system
   * element.
   *
   * @return a random filename to be used as the name of a file system element
   */
  public String getName() {
    String out = null;
    int index = (int) Math.floor(Math.random() * this.names.length);
    out = this.names[index];
    return out;
  }

}
