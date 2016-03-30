//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public class WebPageReader {

  /**
   *
   */
  private String[] pages;

  /**
   *
   *
   * @param filePath
   */
  public WebPageReader(String filePath) {
    this.pages = this.loadPages(filePath);
  }

  /**
   * @param filePath
   *
   * @return
   */
  private String[] loadPages(String filePath) {
    ArrayList<String> list = new ArrayList<String>();
    try (InputStream fis = WebServerMain.class.getResourceAsStream(filePath)) {
      BufferedReader bf = new BufferedReader(
        new InputStreamReader(fis, "UTF-8")
      );
      String line;
      while ((line = bf.readLine()) != null) {
        if (line.trim().charAt(0) == '#') {
          continue;
        }
        list.add(line);
      };
      bf.close();
      fis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] files = new String[list.size()];
    return list.toArray(files);
  }

  /**
   *
   *
   * @return
   */
  public String getPage() {
    return this.pages[(int) Math.floor(Math.random() * this.pages.length)];
  }

}
