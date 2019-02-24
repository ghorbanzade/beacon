//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* This singleton class provides a bridge that allows separating program
* configurations from the source code. This class fetches configurations
* from a java properties file, given the key to that configuration.
*
* @author Pejman Ghorbanzade
* @see Cli
*/
public final class CliConfig {
  /**
  * This class will have a single instance that has a properties object
  * to include program configurations.
  */
  private static CliConfig instance = null;
  private Properties config = null;

  /**
  * To ensure this class can be instantiated only once, the constructor
  * is declared as private. Upon instantiation, the constructor loads
  * the configuration file.
  */
  private CliConfig() {
    try (InputStream fis = CliConfig.class.getResourceAsStream("/cli.properties")) {
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
  * of the CliConfig class.
  *
  * @return the one and only instance of CliConfig class
  */
  public static CliConfig getInstance() {
    if (instance == null) {
      instance = new CliConfig();
    }
    return instance;
  }

  /**
  * The instance of CliConfig class will allow retrieval of configurations
  * using a string format of the key. In case the key is not found in the
  * configuration file, the method would return null.
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
