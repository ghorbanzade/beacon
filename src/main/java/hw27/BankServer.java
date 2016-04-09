//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see BankClient
 * @see BankAccount
 */
public final class BankServer {

  /**
   *
   */
  private final BankAccount account = new BankAccount();
  private final int port;
  private final int timeout;

  /**
   *
   */
  public BankServer(int port, int timeout) {
    this.port = port;
    this.timeout = timeout;
  }

  /**
   *
   */
  public void init() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("Socket created.");
      while (true) {
        serverSocket.setSoTimeout(this.timeout);
        System.out.printf("listening for a connection on local port %d...%n",
            serverSocket.getLocalPort()
        );
        Socket socket = serverSocket.accept();
        System.out.printf("connection established with remote port %d at %s.%n",
            socket.getPort(), socket.getInetAddress().toString()
        );
        executeCommand(socket);
      }
    } catch (SocketTimeoutException e) {
      System.out.printf("connection timed out.%n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   */
  private void executeCommand(Socket socket) {
    try {
      try {
        Scanner in = new Scanner(socket.getInputStream(), "UTF-8");
        PrintWriter out = new PrintWriter(
            new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true
        );
        System.out.println("I/O setup done");
        while (true) {
          if (in.hasNext()) {
            String command = in.next();
            if ("QUIT".equals(command)) {
              System.out.println("QUIT: Connection being closed.");
              out.println("QUIT accepted. Connection being closed.");
              out.close();
              return;
            }
            accessAccount(command, in, out);
          }
        }
      } finally {
        socket.close();
        System.out.println("connection closed.");
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   *
   */
  private void accessAccount(String command, Scanner in, PrintWriter out) {
    double amount;
    if ("DEPOSIT".equals(command)) {
      amount = in.nextDouble();
      account.deposit(amount);
      System.out.println("DEPOSIT: current balance: " + account.getBalance());
      out.println("DEPOSIT Done. current balance: " + account.getBalance());
    } else if ("WITHDRAW".equals(command)) {
      amount = in.nextDouble();
      account.withdraw(amount);
      System.out.println("WITHDRAW: current balance: " + account.getBalance());
      out.println("WITHDRAW Done. current balance: " + account.getBalance());
    } else if ("BALANCE".equals(command)) {
      System.out.println("BALANCE: current balance: " + account.getBalance());
      out.println("BALANCE accepted. current balance: " + account.getBalance());
    } else {
      System.out.println("invalid command");
      out.println("invalid command. try another command.");
    }
  }

  /**
   *
   */
  public int getPort() {
    return this.port;
  }

}
