//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw29;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
  private final HashMap<File, CacheEntry> cache;
  //private final Lock readLock;
  //private final Lock writeLock;
  private final ReentrantReadWriteLock lock;

  /**
   * A file cache object will have a certain size and initializes
   * the map that relates pages to their meta data.
   *
   * @param threshold the number of key-value pairs
   */
  public FileCache(int threshold) {
    this.threshold = threshold;
    this.cache = new HashMap<File, CacheEntry>();
    this.lock = new ReentrantReadWriteLock();
  }

  /**
   * Retrieves content of a specified file either from the cache or by reading
   * it if it is not available in cache.
   *
   * @param file the file that is requested
   * @return the content of the requested web page
   */
  public byte[] fetch(File file) {
    byte[] out = null;
    this.lock.writeLock().lock();
    try {
      System.out.printf("Requesting %s%n", file.getName());
      if (this.cache.containsKey(file)) {
        this.cache.get(file).update();
        out = this.cache.get(file).getContent();
      } else {
        out = this.cacheFile(file);
      }
    } finally {
      this.lock.writeLock().unlock();
    }
    return out;
  }

  /**
   * Assuming the content is not available in the cache, retrieves
   * the content from the file and updates the cache based on the new request.
   *
   * @param file the file that is requested
   * @return the content of the requested web page
   */
  private byte[] cacheFile(File file) {
    byte[] out = null;
    this.lock.writeLock().lock();
    try {
      byte[] content = this.getContent(file);
      CacheEntry ce = new CacheEntry(file, content);
      if (this.cache.size() < this.threshold) {
        this.cache.put(file, ce);
      } else {
        this.updateCache(file, ce);
      }
      out = content;
    } finally {
      this.lock.writeLock().unlock();
    }
    return out;
  }

  /**
   * Assuming content is not available in the cache, returns the content of
   * the resource requested by the client in form of an array of bytes.
   *
   * @return content of the resource requested by the client
   */
  private byte[] getContent(File file) {
    int len = (int) file.length();
    byte[] buf = new byte[len];
    try (DataInputStream fin = new DataInputStream(
        new FileInputStream(file)
    )) {
      fin.readFully(buf);
    } catch (IOException ex) {
      System.err.printf(
          "unable to fetch file %s%n",
          file.getName()
      );
    }
    return buf;
  }

  /**
   * This abstract method allows subclasses to define how the
   * cache should be updated once a new request is made.
   *
   * @param file the file that is requested
   * @param entry the entry to be inserted in cache
   */
  public abstract void updateCache(File file, CacheEntry entry);

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
  protected HashMap<File, CacheEntry> getCache() {
    HashMap<File, CacheEntry> out = null;
    this.lock.readLock().lock();
    try {
      out = this.cache;
    } finally {
      this.lock.readLock().unlock();
    }
    return out;
  }

}
