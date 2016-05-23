//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw29;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

/**
 * This class represents a cache entry as a wrapper object passed as the
 * value to the file cache hashmap.
 *
 * @author Pejman Ghorbanzade
 * @see FileCache
 * @see WebServer
 */
public class CacheEntry {

  /**
   * each cache entry has a content fetched from the webpage it corresponds
   * to, a date representing the last time it is requested and the number of
   * times it is requested while in the file cache.
   */
  private int count;
  private Date date;
  private final byte[] content;

  /**
   * Once a webpage is loaded into the file cache for the first time,
   * its content is saved, its last request date is updated to current
   * time and its requests count is set to one.
   *
   * @param file the file corresponding to this entry
   * @param content the content of the webpage to be saved in file cache
   */
  public CacheEntry(File file, byte[] content) {
    this.count = 1;
    this.content = (byte[]) content.clone();
    this.date = new Date();
  }

  /**
   * increment request counts and update last request date with current time.
   */
  public void update() {
    this.count++;
    this.date = new Date();
  }

  /**
   * If an attempt is made to print a cache entry, the number of its requests
   * should be printed as its representation.
   *
   * @return a string representation of a cache entry
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%d", this.count));
    return sb.toString();
  }

  /**
   * Two cache entries are identical if they have the same content.
   *
   * @param obj a given object to compare against current object
   * @return whether this object is identical with another given object
   */
  @Override
  public boolean equals(Object obj) {
    boolean out = false;
    if (obj instanceof CacheEntry) {
      CacheEntry entry = (CacheEntry) obj;
      out = Arrays.equals(this.getContent(), entry.getContent());
    }
    return out;
  }

  /**
   * hashcode of a cache entry is identical to the hash code of its content.
   *
   * @return the hashcode used to put this object in a hash map
   */
  @Override
  public int hashCode() {
    return Arrays.hashCode(this.content);
  }

  /**
   * Returns content of the webpage as saved in file cache.
   *
   * @return saved content of the webpage
   */
  public byte[] getContent() {
    return (byte[]) this.content.clone();
  }

  /**
   * Returns the number of times the webpage is requested so far.
   *
   * @return the number of times the webpage is requested
   */
  public int getCount() {
    return this.count;
  }

  /**
   * Returns the last time the webpage is requested.
   *
   * @return last time the webpage is requested
   */
  public Date getDate() {
    return (Date) this.date.clone();
  }

}
