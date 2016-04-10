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
public final class BankClientMain {

  /**
   *
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    ConfigReader cr = new ConfigReader("/bank.properties");
    int port = Integer.parseInt(cr.get("server.port"));
    String host = cr.get("host");
    int num = Integer.parseInt(cr.get("clients.number"));
    BankClient[] clients = new BankClient[num];
    for (int i = 0; i < clients.length; i++) {
      clients[i] = new BankClient(port, host);
      clients[i].init();
    }
  }

  /**
   * This class shall not be instantiated.
   */
  private BankClientMain() {
  }

}
