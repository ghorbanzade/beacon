//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A webpage is a wrapper object that holds the file cache.
 *
 * @author Pejman Ghorbanzade
 */
public class WebServer {

  private final FileCache fileCache;

  /**
   * A web server object is initialized by wrapping an instance of
   * file cache that provides logic for saving content and meta data
   * about certain files.
   *
   * @param fc the file cache to be assigned to web server
   */
  public WebServer(FileCache fc) {
    this.fileCache = fc;
  }

  /**
   * This method is provided to handle all the requests made to the
   * server. Upon receipt of a request for a web page, the web server
   * returns the content of the page either from the cache or by
   * reading the file directly.
   *
   * @param name the path to the web page to be requested
   */
  public void request(String name) {
    this.fileCache.fetch(Paths.get(name));
  }

}
