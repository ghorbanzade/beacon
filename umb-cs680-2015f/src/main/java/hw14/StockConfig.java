//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* This singleton class provides a bridge that allows separating
* program configurations from the source code. This class fetches
* configurations from a java properties file.
*
* @author Pejman Ghorbanzade
*/
public final class StockConfig {
  /**
  * This class will have a single instance that has a properties
  * object to include program configurations.
  */
  private static StockConfig instance = null;
  private Properties config = null;

  /**
  * To ensure this class can be instantiated only once, the
  * constructor is declared as private. Upon instantiation, the
  * constructor loads the configuration file.
  */
  private StockConfig() {
    try (InputStream fis = StockConfig.class.getResourceAsStream(
        "/stock.properties")) {
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
  * This factory method allows access to the single instance
  * of the StockConfig class.
  *
  * @return the one and only instance of StockConfig class
  */
  public static StockConfig getInstance() {
    if (instance == null) {
      instance = new StockConfig();
    }
    return instance;
  }

  /**
  * The instance of StockConfig class will allow retrieval of
  * configurations using a string format of the key. In case the
  * key is not found in the configuration file, the method would
  * return null.
  *
  * @param format the format that the key would be given
  * @param arguments the argumnts to the format of the key
  * @return the value corresponding to the given key
  */
  public String get(String format, Object... arguments) {
    String key = String.format(format, arguments);
    return (this.config == null) ? "" : this.config.getProperty(key, "");
  }
}
