//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw29;

/**
 * A sample impelentation of the servlet class.
 *
 * @author Pejman Ghorbanzade
 */
public final class HelloWorld extends HttpServlet {

  /**
   * Returns the string <pre>Hello World</pre> as the servlet output.
   *
   * @return the string <pre>Hello World</pre> as the servlet output
   */
  @Override
  public String getOutput() {
    return "Hello World";
  }

}
