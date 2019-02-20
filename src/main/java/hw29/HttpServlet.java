//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw29;

/**
 * Defines servlet-like programs that are created and executed per user
 * request.
 *
 * @author Pejman Ghorbanzade
 */
public abstract class HttpServlet {

  /**
   * Returns an output to be shown to the user requesting a servlet.
   *
   * @return output of the servlet like classes that extends this class
   */
  public abstract String getOutput();

}
