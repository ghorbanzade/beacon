
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw14;

import java.util.Date;
import java.nio.file.Path;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public class CacheEntry {

  /**
   *
   */
  private int count;
  private Path path;
  private Date date;
  private StringBuilder content;

  /**
   *
   *
   * @param
   */
  public CacheEntry(Path path, StringBuilder content) {
    this.count = 1;
    this.path = path;
    this.content = content;
    this.date = new Date();
  }

  /**
   *
   *
   *
   */
  public void update() {
    this.count++;
    this.date = new Date();
  }

  /**
   *
   *
   *
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    //sb.append(String.format("%s %d %s %s", this.path, this.count,
    //    this.date.toString(), this.content));
    sb.append(String.format("%d", this.count));
    return sb.toString();
  }

  /**
   *
   *
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    boolean out = false;
    if (obj instanceof CacheEntry) {
      CacheEntry entry = (CacheEntry) obj;
      out = this.getContent().equals(entry.getContent());
    }
    return out;
  }

  /**
   *
   *
   * @return
   */
  @Override 
  public int hashCode() {
    return this.content.hashCode();
  }

  /**
   *
   *
   * @return
   */
  public StringBuilder getContent() {
    return this.content;
  }

  /**
   *
   *
   * @return
   */
  public int getCount() {
    return this.count;
  }

  /**
   *
   *
   * @return
   */
  public Date getDate() {
    return this.date;
  }

}
