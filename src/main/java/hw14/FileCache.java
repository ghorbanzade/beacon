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

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public abstract class FileCache {

  /**
   *
   */
  private int threshold;
  private final HashMap<Path, CacheEntry> cache;

  /**
   *
   *
   * @param threshold the number of key-value pairs
   */
  public FileCache(int threshold) {
    this.threshold = threshold;
    this.cache = new HashMap<Path, CacheEntry>();
  }

  /**
   *
   *
   * @param path the path to the requested file
   * @return
   */
  public String fetch(Path path) {
    if (this.cache.containsKey(path)) {
      this.cache.get(path).update();
      return this.cache.get(path).getContent().toString();
    } else {
      return this.cacheFile(path);
    }
  }

  /**
   *
   *
   * @param path the path to the requested file
   * @return
   */
  private String cacheFile(Path path) {
    StringBuilder content = this.getContent(path);
    CacheEntry ce = new CacheEntry(path, content);
    if (this.cache.size() < this.threshold) {
      this.cache.put(path, ce);
    } else {
      this.updateCache(path, ce);
    }
    return content.toString();
  }

  /**
   *
   *
   * @param path the path to the requested file
   * @return
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
   *
   *
   * @param path the path to the requested file
   * @param entry the entry to be inserted in cache
   */
  public abstract void updateCache(Path path, CacheEntry entry);

  /**
   * This method prints key-value pair entries of cached files
   */
  public void showStats() {
    System.out.println(this.cache.entrySet());
  }

  /**
   *
   *
   * @return
   */
  protected HashMap<Path, CacheEntry> getCache() {
    return this.cache;
  }

}
