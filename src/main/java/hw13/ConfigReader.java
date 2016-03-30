//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public final class ConfigReader {

  /**
   *
   */
  private Properties config = null;

  /**
   *
   *
   * @param path
   */
  public ConfigReader(String path) {
    try (InputStream fis = ConfigReader.class.getResourceAsStream(path)) {
      this.config = new Properties();
      this.config.load(fis);
      fis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   *
   * @param args
   * @return
   */
  public String get(Object... args) {
    Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
    String key = String.format((String) args[0], arguments);
    return (this.config == null) ? "" : this.config.getProperty(key, "");
  }

}
