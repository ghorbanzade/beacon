//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

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
  * Each enum value will have a string representation.
  */
  private final String name;

  /**
  * This private constructor will initialize name attribute for an enumeration
  * with its string literal value.
  *
  * @param name string literal of the file system type enumeration
  */
  private FileSystemElementType(String name) {
    this.name = name;
  }

  /**
  * This method will return a string literal of the name associated with
  * the file system element type.
  *
  * @return the name associated with the file system element type
  */
  public String getName() {
    return this.name;
  }
}
