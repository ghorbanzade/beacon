//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw29;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Defines a client request for a specific resource from the web server.
 *
 * @author Pejman Ghorbanzade
 * @see HttpResponse
 * @see WebServer
 */
public final class HttpRequest {

  private final WebServer ws;
  private final File file;
  private final String version;
  private final HttpRequest.Method method;
  private HttpServlet servlet;

  /**
   * Creates an object wrapper holding information about client's request.
   *
   * @param ws the web server whose resource is requested
   * @param in the input stream of client's request
   * @throws InvalidRequestException if client's request is not properly
   *                                 formatted
   */
  public HttpRequest(WebServer ws, BufferedReader in)
      throws InvalidRequestException {
    this.ws = ws;
    try {
      String line = in.readLine();
      System.out.println(line);
      Objects.requireNonNull(line);
      StringTokenizer tokens = new StringTokenizer(line);
      this.method = initMethod(tokens.nextToken());
      this.file = new File(this.ws.getConfig("root.dir") + tokens.nextToken());
      this.version = tokens.nextToken();
      this.processClientRequest(in);
      initServlet();
    } catch (IOException | NoSuchElementException | NullPointerException ex) {
      throw new InvalidRequestException();
    }
  }

  /**
   * Prints important information regarding client's request.
   *
   * @param in the input stream of client's request
   * @throws IOException if client's request cannot be read
   */
  private void processClientRequest(BufferedReader in) throws IOException {
    String line;
    int contentLength = 0;
    while ((line = in.readLine()) != null) {
      if (line.startsWith("Content-Length")) {
        contentLength = Integer.parseInt(
            line.substring("Content-Length: ".length())
        );
      }
      if (line.isEmpty()) {
        if (this.method == HttpRequest.Method.POST) {
          char[] content = new char[contentLength];
          if (in.read(content) != -1) {
            System.out.println(new String(content));
          }
        }
        break;
      }
    }
  }

  /**
   * Returns an http method item for a given string representation of
   * that method. Used to parse method from client's request message.
   *
   * @param method a string representation of the http method of request
   * @return the http method of client request
   */
  private HttpRequest.Method initMethod(String method) {
    try {
      return HttpRequest.Method.valueOf(method);
    } catch (IllegalArgumentException ex) {
      return HttpRequest.Method.INVALID;
    }
  }

  /**
   * checks whether client is asking for a valid servlet.
   */
  public void initServlet() {
    this.servlet = null;
    if (this.file.getPath().contains("servlet")) {
      try {
        String clsName = String.format("%s.%s",
            this.getClass().getPackage().getName(),
            this.file.getName()
        );
        Class cls = Class.forName(clsName);
        Object obj = cls.newInstance();
        this.servlet = (HttpServlet) obj;
      } catch (ClassNotFoundException
          | IllegalAccessException
          | InstantiationException ex
      ) {
      }
    }
  }

  /**
   * Returns the file requested by the client.
   *
   * @return the file requested by the client
   */
  public File getFile() {
    return this.file;
  }

  /**
   * Returns the http version indicated in client's request.
   *
   * @return the http version indicated in client's request
   */
  public String getVersion() {
    return this.version;
  }

  /**
   * Returns the http method of client's request. Returns invalid if the
   * http method in client's request is not supported.
   *
   * @return the http method of client's request
   */
  public HttpRequest.Method getMethod() {
    return this.method;
  }

  /**
   * Returns the servlet subclass instance or null if page is not a servlet.
   *
   * @return the servlet subclass instance or null if page is not a servlet
   */
  public HttpServlet getServlet() {
    return this.servlet;
  }

  /**
   * Returns whether request is for an existing servlet.
   *
   * @return true if client is requesting a servlet
   */
  public boolean isServlet() {
    return (this.servlet == null) ? false : true;
  }

  /**
   * Defines the http methods supported in this program.
   *
   * @author Pejman Ghorbanzade
   */
  public static enum Method {
    GET,
    HEAD,
    POST,
    INVALID;
  }

  /**
   * Thrown when a client request cannot be properly parsed.
   *
   * @author Pejman Ghorbanzade
   * @see HttpRequest
   */
  public static class InvalidRequestException extends RuntimeException {
    // intentionally left blank
  }

}
