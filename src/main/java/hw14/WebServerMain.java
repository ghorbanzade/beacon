//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw14;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public final class WebServerMain {

  /**
   *
   *
   * @param args command line arguments given to this program
   */
  public static void main(String[] args) {
    ConfigReader cfg = new ConfigReader("/webserver.properties");
    int count = Integer.parseInt(cfg.get("webpages.requests"));
    int cacheSize = Integer.parseInt(cfg.get("webserver.filecache.size"));
    FileCache fc = new FileCacheLRU(cacheSize);
    WebServer ws = new WebServer(fc);
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
  }

  /**
   * This class should not be instantiated.
   */
  private WebServerMain() {
  }

}
