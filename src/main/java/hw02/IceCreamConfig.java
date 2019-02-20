//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* This class provides access to different parameters stored in a single
* application configurations file.
* Design of this class follows a classic singleton pattern which allows
* lazy instantiation of one single IceCreamConfig object.
*
* @author Pejman Ghorbanzade
*/
public final class IceCreamConfig {
  private static IceCreamConfig instance = null;
  private Properties config = null;

  /**
  * To disallow instantiation of our singleton class constructor is defined
  * as private. Once the signle instance is being construced, properties
  * object will be initialized.
  */
  private IceCreamConfig() {
    try (InputStream fis =
	IceCreamConfig.class.getResourceAsStream("/icecream.properties")
    ) {
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
  * This static method is used to access the one and only instance of the
  * IceCreamConfig class.
  *
  * @return the one and only instance of the IceCreamConfig class
  */
  public static IceCreamConfig getInstance() {
    if (instance == null) {
      instance = new IceCreamConfig();
    }
    return instance;
  }

  /**
  * This method looks up the value of a given key in the application
  * configuration file. The return value will be empty in case the file is
  * not found or if the key is not found in the file.
  *
  * @param format the string format to be given to String.format method such
  *               that it represents the key to search for in configuration
  *               file
  * @param arguments the arguments to be given to String.format method such
  *               that it represents the key to search for in configuration
  *               file
  * @return the value of given key as specifed in application configuration
  *               file
  */
  public String get(String format, Object... arguments) {
    String key = String.format(format, arguments);
    return (this.config == null) ? "" : this.config.getProperty(key, "");
  }
}
