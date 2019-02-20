//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw27;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * A bank server holds all account information and provides a listening
 * socket for its clients through which they can perform multiple
 * transactions.
 *
 * @author Pejman Ghorbanzade
 * @see BankAccount
 * @see BankClient
 * @see BankServerRunnable
 */
public final class BankServer {

  private final BankAccount account = new BankAccount();
  private final int port;
  private final int timeout;

  /**
   * A bank server needs the port on which it should listen for incoming
   * client requests and the time it should wait before destroying its
   * connection to clients.
   *
   * @param port the port to which the server should listen
   * @param timeout the time in ms that the server listens for incoming
   *                requests before dropping the connection
   */
  public BankServer(int port, int timeout) {
    this.port = port;
    this.timeout = timeout;
  }

  /**
   * A bank server begins its operation by creating a socket and listening
   * to it for client calls. Once a client request is received, the server
   * initiates a thread that responds to that request.
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
   * Returns the port on which the server is listening.
   *
   * @return the port the server is listening on
   */
  public int getPort() {
    return this.port;
  }

}
