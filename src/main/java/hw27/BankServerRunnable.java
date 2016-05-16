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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * A bank server assigns every operation to a thread which receives the
 * transaction command and tries to carry out the request, pritning
 * information on the output as necessary.
 *
 * @author Pejman Ghorbanzade
 * @see BankServer
 */
public final class BankServerRunnable implements Runnable {

  private final Socket socket;
  private final BankAccount account;

  /**
   * A thread needs to know to which socket it should listen and on which
   * account it should operate.
   *
   * @param socket the socket to which the server listens
   * @param account the account on which the transaction should be performed
   */
  public BankServerRunnable(Socket socket, BankAccount account) {
    this.socket = socket;
    this.account = account;
  }

  /**
   * A server thread simply reads a command from the socket and performs the
   * operation the command specifies.
   */
  @Override
  public void run() {
    try {
      try {
        Scanner in = new Scanner(this.socket.getInputStream(), "UTF-8");
        PrintWriter out = new PrintWriter(
            new OutputStreamWriter(
                this.socket.getOutputStream(), "UTF-8"),
                true
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
        this.socket.close();
        System.out.println("connection closed.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Reads a transaction command from socket and performs the requested
   * operation on an account.
   *
   * @param command the string literal of the transaction to perform
   * @param in the buffer from which transaction should be read
   * @param out the buffer to which messages should be written
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

}
