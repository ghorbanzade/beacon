//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see BankClient
 * @see BankAccount
 */
public class BankServer {

  /**
   *
   */
  private final BankAccount account;

  /**
   *
   */
  public BankServer() {
    account = new BankAccount();
  }

  /**
   *
   */
  public void init() {
    ConfigReader cr = new ConfigReader("/bank.properties");
    int port = Integer.parseInt(cr.get("port"));
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Socket created.");
      while (true) {
        System.out.printf("listening for a connection on local port %d...%n",
            serverSocket.getLocalPort()
        );
        Socket socket = serverSocket.accept();
        System.out.printf("connection established with remote port %d at %s.%n",
            socket.getPort(), socket.getInetAddress().toString()
        );
        executeCommand(socket);
      }
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
        PrintWriter out = new PrintWriter(socket.getOutputStream());
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
        System.out.println("A connection is closed.");
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
      System.out.println("DEPOSIT: Current balance: " + account.getBalance());
      out.println("DEPOSIT Done. Current balance: " + account.getBalance());
    } else if ("WITHDRAW".equals(command)) {
      amount = in.nextDouble();
      account.withdraw(amount);
      System.out.println("WITHDRAW: Current balance: " + account.getBalance());
      out.println("WITHDRAW Done. Current balance: " + account.getBalance());
    } else if ("BALANCE".equals(command)) {
      System.out.println("BALANCE: Current balance: " + account.getBalance());
      out.println("BALANCE accepted. Current balance: " + account.getBalance());
    } else {
      System.out.println("Invalid Command");
      out.println("Invalid command. Try another command.");
    }
    out.flush();
  }

  /**
   *
   */
  public static void main(String[] args) {
    BankServer server = new BankServer();
    server.init();
  }

}
