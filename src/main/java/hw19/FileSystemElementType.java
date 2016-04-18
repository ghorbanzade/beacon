//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

/**
 * This enumeration class provides possible types for a file system element.
 *
 * @author Pejman Ghorbanzade
 * @see FileSystemElement
 */
public enum FileSystemElementType {

  /**
   * A file system element is either one of the following.
   */
  FILE ("file"),
  LINK ("link"),
  DIRECTORY ("directory");

  /**
   * Each enum value has a string representation.
   */
  private final String name;

  /**
   * This private constructor will intitialize name attribute fo an enum
   * with its string literal value.
   */
  private FileSystemElementType(String name) {
    this.name = name;
  }

  /**
   * This method gives a string representation of the enum value which is
   * useful for printing purposes.
   *
   * @return the name associated with the file system element type
   */
  public String getName() {
    return this.name;
  }

}
