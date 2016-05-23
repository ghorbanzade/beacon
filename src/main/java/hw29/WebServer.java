//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw29;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Creates the web server that responds to client requests.
 *
 * @author Pejman Ghorbanzade
 */
public class WebServer {

  private final ConfigReader cr;
  private final FileCache fc;

  /**
   * Creates a web server based on a given configuration file.
   *
   * @param path the filepath to the web server configuration file
   */
  public WebServer(String path) {
    this.cr = new ConfigReader(path);
    this.fc = initFileCache(
        this.cr.get("filecache.method"),
        Integer.parseInt(this.cr.get("filecache.size"))
    );
  }

  /**
   * Creates a socket on a pre-configured port number and sets the timeout to
   * listen to clients requests.
   */
  public void init() {
    int port = Integer.parseInt(this.cr.get("port"));
    int timeout = Integer.parseInt(this.cr.get("server.timeout"));
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Socket created.");
      while (true) {
        serverSocket.setSoTimeout(timeout);
        System.out.printf("waiting for connection on local port %d...%n",
            serverSocket.getLocalPort()
        );
        Socket socket = serverSocket.accept();
        System.out.printf("connection established with remote port %d at %s.%n",
            socket.getPort(), socket.getInetAddress().toString()
        );
        new Thread(new ConnectionHandler(this, socket)).start();
      }
    } catch (SocketTimeoutException ex) {
      System.out.printf("connection timed out.%n");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Creates a file cache object with a given size and based on a specified
   * policy.
   *
   * @param method the name of the algorithm to cache files
   * @param size the size of the file cache
   * @return an instance of file cache
   */
  private FileCache initFileCache(String method, int size) {
    if ("lru".equalsIgnoreCase(method)) {
      return new FileCacheLRU(size);
    } else if ("lfu".equalsIgnoreCase(method)) {
      return new FileCacheLFU(size);
    } else {
      return null;
    }
  }

  /**
   * Returns a particular configuration parameter based on a given key.
   *
   * @param args the number of strings whose format constructs the key
   * @return the configuration value assigned to the given key
   */
  public String getConfig(Object... args) {
    return this.cr.get(args);
  }

  /**
   * Handles all file requests made to the server. Upon receipt of a
   * request for a web page, the web server returns content of the page
   * either from the cache or by reading the file directly.
   *
   * @param name the file that the client is requesting
   * @return content of a requested file
   */
  public byte[] request(File file) {
    return this.fc.fetch(file);
  }

}
