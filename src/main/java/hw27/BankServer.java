//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.ServerSocket;

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
   *
   * @param port
   * @param timeout
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
        new Thread(new BankServerRunnable(socket, this.account)).start();
      }
    } catch (SocketTimeoutException e) {
      System.out.printf("connection timed out.%n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   *
   * @return
   */
  public int getPort() {
    return this.port;
  }

}
