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
public final class WebServerMain {

  /**
   *
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    WebServer ws = new WebServer();
    ConfigReader cfg = new ConfigReader("/webserver.properties");
    int count = Integer.parseInt(cfg.get("webpages.requests"));
    RequestHandler rh = new RequestHandler(ws);
    Thread[] threads = new Thread[count];
    for (int i = 0; i < count; i++) {
      threads[i] = new Thread(rh);
      threads[i].start();
    }
  }

  /**
   * This class must not be instantiated.
   */
  private WebServerMain() {
  }

}
