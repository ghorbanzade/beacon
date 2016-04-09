//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

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
  private Scanner in;
  private PrintWriter out;

  /**
   *
   */
  public void init() {
    ConfigReader cr = new ConfigReader("/bank.properties");
    int port = Integer.parseInt(cr.get("port"));
    try (Socket socket = new Socket(cr.get("host"), port)) {
      this.socket = socket;
      System.out.printf("socket created on local port %d%n",
          socket.getLocalPort());
      System.out.printf("connection established with remote port %d at %s%n",
          socket.getPort(), socket.getInetAddress().toString());
      in = new Scanner(socket.getInputStream(), "UTF-8");
      out = new PrintWriter(socket.getOutputStream());
      System.out.println("I/O setup done.");
      sendCommands();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   */
  public void sendCommands() {
    sendCommand("BALANCE\n");
    System.out.println(getResponse());
    sendCommand("DEPOSIT 100\n");
    System.out.println(getResponse());
    sendCommand("WITHDRAW 50\n");
    System.out.println(getResponse());
    sendCommand("QUIT\n");
  }

  /**
   *
   */
  public void sendCommand(String command) {
    System.out.printf("Sending %s", command);
    out.print(command);
    out.flush();
    if ("QUIT".equals(command)) {
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
  public String getResponse() {
    return in.nextLine();
  }

  /**
   *
   */
  public static void main(String[] args) {
    BankClient client = new BankClient();
    client.init();
  }

}
