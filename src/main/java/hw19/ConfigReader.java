//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * This class allows defining configuration parameters in a separate properties
 * file instead of resorting to hard-code them.
 *
 * @author Pejman Ghorbanzade
 */
public final class ConfigReader {

  /**
   * Since the configuration file to be read is a java properties file
   * an object of this class would have a properties attribute to hold
   * the properties in memory.
   */
  private final Properties config = new Properties();

  /**
   * Upon instantiation, an object of this class will read content of
   * a given properties file and loads all properties for easy access.
   *
   * @param path the path to the properties file to parse
   */
  public ConfigReader(String path) {
    try (InputStream fis = ConfigReader.class.getResourceAsStream(path)) {
      this.config.load(fis);
      fis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to access the value of a property. It accepts
   * a variable number of strings, first of which, indicates the format
   * for constructing the key.
   *
   * @param args the number of strings whose format constructs the key
   * @return the value assigned to the given key
   */
  public String get(Object... args) {
    Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
    String key = String.format((String) args[0], arguments);
    return (this.config == null) ? "" : this.config.getProperty(key, "");
  }

}
