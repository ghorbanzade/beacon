//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;

/**
 *
 *
 * @author Pejman Ghorbanzade
 */
public class AccessCounter {

  /**
   *
   */
  private HashMap<Path, Integer> hm = new HashMap<Path, Integer>();

  /**
   *
   */
  public AccessCounter() {
  }

  /**
   *
   *
   * @param path
   */
  public void increment(Path path) {
    int count = this.getCount(path);
    this.hm.put(path, count + 1);
  }

  /**
   *
   *
   * @param path
   */
  public int getCount(Path path) {
    return this.hm.containsKey(path) ? this.hm.get(path) : 0;
  }

}
