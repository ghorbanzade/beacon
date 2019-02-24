//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw29;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Vector;

/**
 * Creates the web server that responds to client requests.
 *
 * @author Pejman Ghorbanzade
 */
public class WebServer {

  private final ConfigReader cr;
  private final FileCache fc;
  private final ThreadPool tp;

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
    this.tp = new ThreadPool(
        Integer.parseInt(this.cr.get("threadpool.size"))
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
        this.tp.execute(
            new ConnectionHandler(this, socket)
        );
      }
    } catch (SocketTimeoutException ex) {
      System.out.printf("connection timed out.%n");
      this.tp.shutdown();
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

  /**
   * A threadpool is a singleton class that creates as many threads as
   * specified in the configuration file and places them in a queue, the
   * first time it is instantiated. The singleton class provides methods
   * to use those threads to execute given tasks.
   *
   * @author Pejman Ghorbanzade
   * @see ThreadPoolThread
   */
  public static final class ThreadPool {

    /**
     * The thread pool instance has a vector on which it places its threads
     * and a queue on which it places the tasks awaiting execution by those
     * threads.
     */
    private final TaskQueue queue;
    private final Vector<ThreadPoolThread> availableThreads;

    /**
     * Singleton class should not be directly instantiated.
     */
    public ThreadPool(int size) {
      queue = new TaskQueue();
      availableThreads = new Vector<ThreadPoolThread>();
      for (int i = 0; i < size; i++) {
        ThreadPoolThread th = new ThreadPoolThread(queue, i);
        availableThreads.add(th);
        th.start();
      }
    }

    /**
     * This method is called by user when he needs a task to be executed.
     * The task is placed on a queue waiting to be executed as soon as a
     * thread is available.
     *
     * @param task the task to be executed
     */
    public void execute(ConnectionHandler task) {
      this.queue.put(task);
    }

    /**
     * This method allows user to see how many tasks are on the queue
     * awaiting executino.
     *
     * @return the number of tasks on the queue awaiting execution
     */
    public int getTaskQueueSize() {
      return this.queue.size();
    }

    /**
     * This method allows user to see how many threads exist in the
     * pool.
     *
     * @return the number of threads available to execute tasks
     */
    public int getThreadPoolSize() {
      return this.availableThreads.size();
    }

    /**
     * This method is called by user to stop all execution of all
     * threads which itself will lead to a halt on execution of all
     * tasks.
     */
    public void shutdown() {
      for (ThreadPoolThread t: availableThreads) {
        t.setFlag();
        t.interrupt();
      }
    }

  }

}
