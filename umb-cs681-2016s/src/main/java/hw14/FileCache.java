//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw14;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This abstract class defines a fixed-size map that relates
 * pages with their meta data and leaves the logic of how to
 * update the map to the classes that will extend it.
 *
 * @author Pejman Ghorbanzade
 * @see WebServer
 * @see FileCacheLFU
 * @see FileCacheLRU
 */
public abstract class FileCache {

  /**
   * A file cache can have a fixed number of key-value pairs in a
   * cache that relates pages with their meta data.
   */
  private final int threshold;
  private final ReentrantLock lock;
  private final HashMap<Path, CacheEntry> cache;

  /**
   * A file cache object will have a certain size and initializes
   * the map that relates pages to their meta data.
   *
   * @param threshold the number of key-value pairs
   */
  public FileCache(int threshold) {
    this.threshold = threshold;
    this.lock = new ReentrantLock();
    this.cache = new HashMap<Path, CacheEntry>();
  }

  /**
   * This method takes the path to a page and tries to retrieve its
   * content from the cache. If the content is not available in the
   * cache, the content is fetched from the file.
   *
   * @param path the path to the requested file
   * @return the content of the requested web page
   */
  public String fetch(Path path) {
    String out = null;
    this.lock.lock();
    try {
      System.out.printf("Requesting %s%n", path.toString());
      if (this.cache.containsKey(path)) {
        this.cache.get(path).update();
        out = this.cache.get(path).getContent().toString();
      } else {
        out = this.cacheFile(path);
      }
    } finally {
      this.lock.unlock();
    }
    return out;
  }

  /**
   * This method takes the path to a page and assuming the content is
   * not available in the cache, retrieves the content from the file
   * and updates the cache based on the new request.
   *
   * @param path the path to the requested file
   * @return the content of the requested web page
   */
  private String cacheFile(Path path) {
    String out = null;
    this.lock.lock();
    try {
      StringBuilder content = this.getContent(path);
      CacheEntry ce = new CacheEntry(path, content);
      if (this.cache.size() < this.threshold) {
        this.cache.put(path, ce);
      } else {
        this.updateCache(path, ce);
      }
      out = content.toString();
    } finally {
      this.lock.unlock();
    }
    return out;
  }

  /**
   * This method takes the path to a page and assuming the content is
   * not available in the cache, tries to retrieve content of the page
   * from the file directly.
   *
   * @param path the path to the requested file
   * @return an instance of string builder holding content of the file
   */
  private StringBuilder getContent(Path path) {
    String filePath = String.format("/files/%s.txt", path.toString());
    StringBuilder sb = new StringBuilder();
    try (InputStream fis = FileCache.class.getResourceAsStream(filePath)) {
      BufferedReader bf = new BufferedReader(
          new InputStreamReader(fis, "UTF-8")
      );
      String line;
      while ((line = bf.readLine()) != null) {
        sb.append(line);
      }
      bf.close();
      fis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb;
  }

  /**
   * This abstract method allows subclasses to define how the
   * cache should be updated once a new request is made.
   *
   * @param path the path to the requested web page
   * @param entry the entry to be inserted in cache
   */
  public abstract void updateCache(Path path, CacheEntry entry);

  /**
   * This method prints key-value pair entries of cached files.
   */
  public void showStats() {
    System.out.println(this.cache.entrySet());
  }

  /**
   * This method gives access to the map that relates web pages
   * to saved meta data.
   *
   * @return the map that relates web pages to saved meta data
   */
  protected HashMap<Path, CacheEntry> getCache() {
    HashMap<Path, CacheEntry> out = null;
    this.lock.lock();
    try {
      out = this.cache;
    } finally {
      this.lock.unlock();
    }
    return out;
  }

}
