//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
*
*
* @author Pejman Ghorbanzade
* @see Cli
*/
public final class CliConfig {
  /**
  *
  */
  private static CliConfig instance = null;
  private Properties config = null;

  /**
  *
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
  *
  *
  * @return
  */
  public static CliConfig getInstance() {
    if (instance == null) {
      instance = new CliConfig();
    }
    return instance;
  }

  /**
  *
  *
  * @param format
  * @param arguments
  * @return
  */
  public String get(String format, Object... arguments) {
    String key = String.format(format, arguments);
    return (this.config == null) ? "" : this.config.getProperty(key, "");
  }
}
