//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class defines the server that echoes input given on its socket.
 *
 * @author Pejman Ghorbanzade
 * @see EchoClientMain
 */
public final class EchoServerMain {

  /**
   * This program creates a socket on a specific port and awaits a client
   * to connect to the socket. The server echoes client's message to the
   * socket.
   *
   * @param args command line arguments given to program
   */
  public static void main(String[] args) {
    ConfigReader cr = new ConfigReader("/echo.properties");
    int port = Integer.parseInt(cr.get("port"));
    try (
        ServerSocket ss = new ServerSocket(port);
        Socket cs = ss.accept();
        PrintWriter out = new PrintWriter(
            new OutputStreamWriter(cs.getOutputStream(), "UTF-8"), true
        );
        BufferedReader in = new BufferedReader(
            new InputStreamReader(cs.getInputStream(), "UTF-8")
        );
    ) {
      String line;
      while ((line = in.readLine()) != null) {
        System.out.printf("client: %s%n", line);
        out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This class should not be instantiated.
   */
  private EchoServerMain() {
  }

}
