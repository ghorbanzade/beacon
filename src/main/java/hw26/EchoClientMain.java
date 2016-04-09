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
import java.net.Socket;

/**
 * This class defines the client that sends user inputs to a server and
 * prints output of the server on standard output.
 *
 * @author Pejman Ghorbanzade
 * @see EchoServerMain
 */
public final class EchoClientMain {

  /**
   * This program creates a socket to connect to the server and communicate
   * user inputs to the server and prints the output of server on standard
   * output.
   *
   * @param args command line arguments given to program
   */
  public static void main(String[] args) {
    ConfigReader cr = new ConfigReader("/echo.properties");
    int port = Integer.parseInt(cr.get("port"));
    try (
        Socket es = new Socket(cr.get("host"), port);
        PrintWriter out = new PrintWriter(
            new OutputStreamWriter(es.getOutputStream(), "UTF-8"), true
        );
        BufferedReader in = new BufferedReader(
            new InputStreamReader(es.getInputStream(), "UTF-8")
        );
        BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in, "UTF-8")
        );
    ) {
      System.out.printf("connection established.%n");     
      System.out.printf("type 'exit' to disconnect.%n");
      System.out.printf("--------------------------%n");
      while (true) {
        System.out.printf("client: ");
        String line = stdin.readLine();
        if (line == null) {
          break;
        }
        out.println(line);
        System.out.printf("server: %s%n", in.readLine());
        if ("exit".equalsIgnoreCase(line)) {
          break;
        }
      }
      System.out.printf("connection terminated%n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This class should not be instantiated.
   */
  private EchoClientMain() {
  }

}
