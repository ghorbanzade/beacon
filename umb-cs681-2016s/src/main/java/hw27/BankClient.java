//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Defines a client as an agent that connects to bank server to request
 * multiple transactions be made on his account.
 *
 * @author Pejman Ghorbanzade
 * @see BankServer
 */
public class BankClient {

  private final String id;
  private final int remotePort;
  private final String remoteHost;

  /**
   * A client has a unique random string identifier and the host and port
   * on which it should create its socket.
   *
   * @param remotePort the port on which client should create its socket
   * @param remoteHost the host on which client should create its socket
   */
  public BankClient(int remotePort, String remoteHost) {
    this.remotePort = remotePort;
    this.remoteHost = remoteHost;
    this.id = new Identifier().get();
  }

  /**
   * A bank client creates a socket to connet to the bank server and
   * makes multiple transactions on his bank account using that socket.
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
   * Generates multiple transactions and transmits them to the bank server.
   * TODO: transactions to be transmitted should be read from a file.
   *
   * @param socket the client socket which should be closed if a transaction
   *               terminates client-server connection.
   * @param in the server ouput buffer that the client is reading
   * @param out the client output buffer
   * @throws IOException in case socket connection is interrupted
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
   * Requests a transaction from bank server and outputs server response on
   * standard output.
   *
   * @param socket the client socket which should be closed if the command
   *               terminates client-server connection.
   * @param in the server output buffer that the client is reading
   * @param out the client output buffer
   * @param command the transaction to be made
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
   * Prints server response on the standard output.
   *
   * @param in output stream of the server socket to which client is listening
   * @throws IOException if an I/O error occurs when reading server buffer
   */
  private void showResponse(BufferedReader in) throws IOException {
    System.out.println(in.readLine());
  }

}
