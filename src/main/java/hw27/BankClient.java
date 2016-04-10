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
import java.util.ArrayList;

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
  private final String id;
  private final int remotePort;
  private final String remoteHost;

  /**
   *
   *
   * @param remotePort
   * @param remoteHost
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
      System.out.printf("%s: socket created on local port %d%n",
          this.id, socket.getLocalPort());
      System.out.printf("connection established with remote port %d at %s%n",
          socket.getPort(), socket.getInetAddress().toString());
      BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream(), "UTF-8")
      );
      PrintWriter out = new PrintWriter(
          new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true
      );
      System.out.printf("I/O setup done.%n");
      this.sendCommands(socket, in, out);
    } catch (IOException e) {
      System.out.printf("connection refused.%n");
      //e.printStackTrace();
    }
  }

  /**
   *
   *
   * @param socket
   * @param in
   * @param out
   * @throws IOException
   */
  private void sendCommands(Socket socket, BufferedReader in, PrintWriter out)
      throws IOException {
    ArrayList<Transaction> ts = new ArrayList<Transaction>();
    ts.add(new Transaction(TransactionType.BALANCE));
    ts.add(new Transaction(TransactionType.DEPOSIT, 100.0f));
    ts.add(new Transaction(TransactionType.WITHDRAW, 50.0f));
    ts.add(new Transaction(TransactionType.QUIT));
    for (Transaction t: ts) {
      this.sendCommand(socket, in, out, t);
      if (t.getType() != TransactionType.QUIT) {
        this.showResponse(in);
      }
    }
  }

  /**
   *
   *
   * @param socket
   * @param in
   * @param out
   * @param command
   */
  private void sendCommand(Socket socket, BufferedReader in, PrintWriter out,
      Transaction command) {
    System.out.printf("sending %s%n", command.toString());
    out.println(command.toString());
    if ("QUIT".equals(command.toString())) {
      try {
        socket.close();
        System.out.printf("connection closed.%n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   *
   *
   * @param in
   * @throws IOException
   */
  private void showResponse(BufferedReader in) throws IOException {
    System.out.println(in.readLine());
  }

}
