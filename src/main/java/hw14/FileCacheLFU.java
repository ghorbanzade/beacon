//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.util.Map;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see FileCache
 * @see FileCacheLRU
 */
public class FileCacheLFU extends FileCache {

  /**
   *
   *
   * @param threshold
   */
  public FileCacheLFU(int threshold) {
    super(threshold);
  }

  /**
   *
   *
   * @param path
   * @param newEntry
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
