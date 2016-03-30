//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw13;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public class RequestHandler implements Runnable {

  /**
   *
   */
  private final WebServer ws;
  private final WebPageReader wpr;

  /**
   *
   *
   * @param ws
   */
  public RequestHandler(WebServer ws) {
    this.ws = ws;
    ConfigReader cfg = new ConfigReader("/webserver.properties");
    this.wpr = new WebPageReader(cfg.get("webpages.file"));
  }

  /**
   *
   */
  public void run() {
    this.ws.request(this.wpr.getPage());
  }

}
