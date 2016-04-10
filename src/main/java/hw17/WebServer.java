//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A webpage is a wrapper object that holds the file cache.
 *
 * @author Pejman Ghorbanzade
 * @see AccessCounter
 */
public class WebServer {

  /**
   * A web server has a file cache to save information about certain
   * files in it based on the logic implemented in the file cache.
   */
  private final FileCache fileCache;

  /**
   * A web server object is initialized by wrapping an instance of
   * file cache that provides logic for saving content and meta data
   * about certain files.
   *
   * @param fileCache the file cache to be assigned to web server
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
