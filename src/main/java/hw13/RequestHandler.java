//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw13;

/**
 * This class implements the Runnable class to allow its objects
 * to be passed to threads.
 *
 * @author Pejman Ghorbanzade
 */
public class RequestHandler implements Runnable {

  /**
   * A request handler uses a page reader to randomely generate
   * webpage names to request from the web server.
   */
  private final WebServer ws;
  private final WebPageReader wpr;

  /**
   * A request handler should know to which web server requests
   * should be made.
   *
   * @param ws the webserver to which the request is made
   */
  public RequestHandler(WebServer ws) {
    this.ws = ws;
    ConfigReader cfg = new ConfigReader("/webserver.properties");
    this.wpr = new WebPageReader(cfg.get("webpages.file"));
  }

  /**
   * As an implementation of run method for thread class, this method
   * requests a random webpage from the web server.
   */
  public void run() {
    this.ws.request(this.wpr.getPage());
  }

}
