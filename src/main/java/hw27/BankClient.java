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
public class BankClient {

  /**
   *
   */
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private final String id;
  private final int remotePort;
  private final String remoteHost;

  /**
   *
   */
  public BankClient(int remotePort, String remoteHost) {
    this.remotePort = remotePort;
    this.remoteHost = remoteHost;
    this.id = new Identifier().get();
  }

  /**
   *
   */
  public void init() {
    try (Socket socket = new Socket(this.remoteHost, this.remotePort)) {
      this.socket = socket;
      System.out.printf("%s: socket created on local port %d%n",
          this.id, socket.getLocalPort());
      System.out.printf("connection established with remote port %d at %s%n",
          socket.getPort(), socket.getInetAddress().toString());
      this.in = new BufferedReader(
          new InputStreamReader(this.socket.getInputStream(), "UTF-8")
      );
      this.out = new PrintWriter(
          new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true
      );
      System.out.printf("I/O setup done.%n");
      sendCommands();
    } catch (IOException e) {
      System.out.printf("connection refused.%n");
      //e.printStackTrace();
    }
  }

  /**
   *
   */
  private void sendCommands() throws IOException {
    this.sendCommand(new Transaction(TransactionType.BALANCE));
    System.out.println(getResponse());
    this.sendCommand(new Transaction(TransactionType.DEPOSIT, 100.0f));
    System.out.println(getResponse());
    this.sendCommand(new Transaction(TransactionType.WITHDRAW, 50.0f));
    System.out.println(getResponse());
    this.sendCommand(new Transaction(TransactionType.QUIT));
  }

  /**
   *
   */
  private void sendCommand(Transaction command) {
    System.out.printf("sending %s%n", command.toString());
    this.out.println(command.toString());
    if ("QUIT".equals(command.toString())) {
      try {
        this.socket.close();
        System.out.printf("connection closed.%n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   *
   */
  private String getResponse() throws IOException {
    return this.in.readLine();
  }

}
