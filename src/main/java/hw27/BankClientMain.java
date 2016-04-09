//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see BankServer
 */
public class BankClientMain {

  /**
   *
   */
  public static void main(String[] args) {
    ConfigReader cr = new ConfigReader("/bank.properties");
    int port = Integer.parseInt(cr.get("server.port"));
    String host = cr.get("host");
    BankClient client = new BankClient(port, host);
    client.init();
  }

  /**
   * This class shall not be instantiated.
   */
  private BankClientMain() {
  }

}
