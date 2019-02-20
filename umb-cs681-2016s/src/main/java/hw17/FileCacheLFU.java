//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw17;

import java.nio.file.Path;
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
   * @param path the path to the requested web page
   * @param newEntry the new entry with which to update the cache
   */
  @Override
  public void updateCache(Path path, CacheEntry newEntry) {
    int minCount = Integer.MAX_VALUE;
    Path minPath = null;
    for (Map.Entry<Path, CacheEntry> entry: this.getCache().entrySet()) {
      if (minCount >= entry.getValue().getCount()) {
        minCount = entry.getValue().getCount();
        minPath = entry.getKey();
      }
    }
    this.getCache().remove(minPath);
    this.getCache().put(path, newEntry);
    System.out.println(this.getCache());
  }

}
