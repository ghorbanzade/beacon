//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw29;

import java.io.File;
import java.util.Map;

/**
 * This class provides logic for updating file cache of the web server
 * such that it always holds meta data of the most recently requested
 * web pages.
 *
 * @author Pejman Ghorbanzade
 * @see FileCache
 * @see FileCacheLRU
 */
public class FileCacheLFU extends FileCache {

  /**
   * File cache objects are distinguished based on the size of
   * the map they hold to cache meta data of web pages.
   *
   * @param threshold size of the map for caching meta data of pages
   */
  public FileCacheLFU(int threshold) {
    super(threshold);
  }

  /**
   * This method replaces the oldest entry in the file cache with
   * the new cache entry passed to it.
   *
   * @param file the file that is requested
   * @param newEntry the new entry with which to update the cache
   */
  @Override
  public void updateCache(File file, CacheEntry newEntry) {
    int minCount = Integer.MAX_VALUE;
    File minFile = null;
    for (Map.Entry<File, CacheEntry> entry: this.getCache().entrySet()) {
      if (minCount >= entry.getValue().getCount()) {
        minCount = entry.getValue().getCount();
        minFile = entry.getKey();
      }
    }
    this.getCache().remove(minFile);
    this.getCache().put(file, newEntry);
    System.out.println(this.getCache());
  }

}
