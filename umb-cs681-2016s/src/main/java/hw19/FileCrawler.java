//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw19;

/**
 * This class defines a file crawler as a thread that continues to
 * read file system element reachable through a given directory and
 * put them a file queue until all the file system elements are
 * processed or the main thread interrupts the thread.
 *
 * @author Pejman Ghorbanzade
 * @see FileQueue
 * @see File
 */
public final class FileCrawler implements Runnable {

  /**
   * A file crawler holds a reference to the file queue it should put
   * file system elements on, as well as the time it should wait
   * before each file process.
   */
  private final FileQueue fq;
  private final Directory root;
  private final int speed;

  /**
   * Upon construction, a file crawler loads the configuration file to
   * determine the time it should wait between every crawling action.
   *
   * @param root the directory of the file system to crawl
   * @param fq the file queue on which files should be put
   */
  public FileCrawler(Directory root, FileQueue fq) {
    ConfigReader cfg = new ConfigReader("/filesystem.properties");
    this.speed = Integer.parseInt(cfg.get("crawler.speed"));
    this.fq = fq;
    this.root = root;
  }

  /**
   * This recursive method checks children of a directory and puts them
   * on the file queue if they are files. In case they are directories,
   * it tries to crawl them using DFS method.
   *
   * @param root top level directory whose elements should be crawled.
   * @throws InterruptedException when the main thread asks to stop
   */
  private void crawl(Directory root) throws InterruptedException {
    for (FileSystemElement element: root.getChildren()) {
      if (element instanceof Directory) {
        this.crawl((Directory) element);
      } else if (element instanceof File) {
        File file = (File) element;
        while (true) {
          if (this.fq.getFlag()) {
            throw new InterruptedException();
          } else if (this.fq.isFull()) {
            try {
              Thread.sleep(this.speed);
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          } else {
            this.fq.put(file);
            break;
          }
        }
      }
    }
  }

  /**
   * A thread given a file crawler tries to crawl the root directory
   * which is given to the file crawler. It stops either if the crawler
   * has processed all the file system elements under the root directory
   * or the main thread has interrupted the thread.
   */
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        this.crawl(this.root);
        break;
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    System.out.printf("file crawler stopped by the main thread%n");
  }

}
