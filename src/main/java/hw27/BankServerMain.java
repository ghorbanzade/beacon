//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see BankServer
 */
public class BankServerMain {

  /**
   *
   */
  public static void main(String[] args) {
    ConfigReader cr = new ConfigReader("/bank.properties");
    int port = Integer.parseInt(cr.get("server.port"));
    int timeout = Integer.parseInt(cr.get("server.timeout"));
    BankServer server = new BankServer(port, timeout);
    server.init();
  }

  /**
   * This class should not be instantiated.
   */
  private BankServerMain() {
  }

}
