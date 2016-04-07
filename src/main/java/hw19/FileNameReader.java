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
 *
 *
 * @author Pejman Ghorbanzade
 */
public class FileNameReader {

  /**
   *
   */
  private final String[] names;

  /**
   *
   *
   * @param path
   */
  public FileNameReader(String path) {
    this.names = this.loadNames(path);
  }

  /**
   *
   *
   * @param path
   * @return
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
   *
   *
   * @return
   */
  public String getName() {
    String out = null;
    int index = (int) Math.floor(Math.random() * this.names.length);
    out = this.names[index];
    return out;
  }

}
