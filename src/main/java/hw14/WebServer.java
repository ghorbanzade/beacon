//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see AccessCounter
 */
public class WebServer {

  /**
   *
   */
  private final ReentrantLock lock;
  private final FileCache fileCache;

  /**
   *
   *
   * @param fileCache
   */
  public WebServer(FileCache fc) {
    this.fileCache = fc;
    this.lock = new ReentrantLock();
  }

  /**
   *
   *
   * @param name
   */
  public void request(String name) {
    this.lock.lock();
    try {
      System.out.printf("Requesting %s%n", name);
      Path page = Paths.get(name);
      this.fileCache.fetch(page);
    } finally {
      this.lock.unlock();
    }
  }

  /**
   *
   *
   * @return
   */
  public FileCache getFileCache() {
    FileCache cache = null;
    this.lock.lock();
    try {
      cache = this.fileCache;
    } finally {
      this.lock.unlock();
    }
    return cache;
  }

}
