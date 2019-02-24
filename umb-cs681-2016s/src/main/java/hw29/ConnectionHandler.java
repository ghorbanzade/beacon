//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Allows every client request to be handled by a separate thread.
 *
 * @author Pejman Ghorbanzade
 * @see WebServer
 * @see HttpResponse
 * @see HttpRequest
 */
public final class ConnectionHandler implements Runnable {

  private Socket socket;
  private WebServer ws;

  /**
   * Creates a runnable instance to handle a client request by a thread.
   *
   * @param ws the web server whose resource is requested
   * @param socket the socket with which to communicate with the client
   */
  public ConnectionHandler(WebServer ws, Socket socket) {
    this.ws = ws;
    this.socket = socket;
  }

  /**
   * Handles a client request for a specific resource.
   */
  @Override
  public void run() {
    int timeout = Integer.parseInt(this.ws.getConfig("client.timeout"));
    try (
      BufferedReader in = new BufferedReader(
          new InputStreamReader(this.socket.getInputStream(), "UTF-8")
      );
      PrintStream out = new PrintStream(
          this.socket.getOutputStream(), false, "UTF-8"
      );
    ) {
      try {
        this.socket.setSoTimeout(timeout);
        HttpRequest request = new HttpRequest(this.ws, in);
        HttpResponse response = new HttpResponse(this.ws, request, out);
        response.sendResponse();
        out.flush();
      } catch (HttpRequest.InvalidRequestException ex) {
        System.out.printf("invalid request discarded.%n");
      } finally {
        this.socket.close();
        System.out.printf("connection closed.%n");
      }
    } catch (SocketTimeoutException ex) {
      System.out.printf("client took too long to respond.%n");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
