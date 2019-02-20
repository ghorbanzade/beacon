//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw29;

/**
 * Demonstrates how the program to handle http requests works.
 *
 * @author Pejman Ghorbanzade
 * @see WebServer
 */
public final class WebServerMain {

  /**
   * Creates a server to respond to client requests.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    WebServer server = new WebServer("/http.properties");
    server.init();
  }

  /**
   * This class should not be instantiated.
   */
  private WebServerMain() {
  }

}
