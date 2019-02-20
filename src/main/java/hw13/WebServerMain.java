//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw13;

/**
 * This class demonsrates a thread-safe design to handle multiple
 * webpage load requests.
 *
 * @author Pejman Ghorbanzade
 */
public final class WebServerMain {

  /**
   * This program constructs a number of threads that request
   * webpages from a web server. Once all requests are made,
   * the webserver will report the the number of times different
   * webpages are requested.
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
    for (Thread t: threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    ws.getAccessCounter().showStats();
  }

  /**
   * This class must not be instantiated.
   */
  private WebServerMain() {
  }

}
