//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw14;

/**
 * This class demonstrates a thread-safe design for the file cache problem
 * to cache a limited number of webpages based on different criteria.
 *
 * @author Pejman Ghorbanzade
 */
public final class WebServerMain {

  /**
   * This program constructs a number of threads that request webpages
   * from a web server. Upon receipt of a request, the web server will
   * load the content of webpage and return it to the client.
   * To avoid having to load all webpages, the web server uses a file
   * cache to cache a limited number of webpages most sought after.
   * Once all requests are made, the webserver will report the number
   * of times different webpages are requested.
   *
   * @param args command line arguments given to this program
   */
  public static void main(String[] args) {
    ConfigReader cfg = new ConfigReader("/webserver.properties");
    int count = Integer.parseInt(cfg.get("webpages.requests"));
    int cacheSize = Integer.parseInt(cfg.get("webserver.filecache.size"));
    String cacheMethod = cfg.get("webserver.filecache.method");
    FileCache fc = initFileCache(cacheMethod, cacheSize);
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
   * this method takes the name of the method retrieved from the
   * configuration file and constructs an object from a  subclass
   * of file cache, corresponding to the method name.
   *
   * @param method the name of the algorithm to cache files
   * @param size the size of the file cache
   * @return an instance of file cache
   */
  public static FileCache initFileCache(String method, int size) {
    if ("lru".equalsIgnoreCase(method)) {
      return new FileCacheLRU(size);
    } else if ("lfu".equalsIgnoreCase(method)) {
      return new FileCacheLFU(size);
    } else {
      return null;
    }
  }

  /**
   * This class should not be instantiated.
   */
  private WebServerMain() {
  }

}
