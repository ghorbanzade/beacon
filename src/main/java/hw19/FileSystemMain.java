//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;

/**
 * This class demonstrates how a file system can be crawler by multiple
 * threads and indexed by other threads at the same time.
 *
 * @author Pejman Ghorbanzade
 */
public final class FileSystemMain {

  /**
   * This program loads a file system and creates as many file crawlers
   * as there are directories under the root directory, and as many
   * file indexers as specified in configuration file. The main thread
   * then starts crawlers and indexers as separate threads and waits
   * a certain amount of time before stopping them.
   *
   * @param args command line arguments given to the problem
   */
  public static void main(String[] args) {
    ConfigReader cfg = new ConfigReader("/filesystem.properties");
    FileSystem fs = new FileSystem(cfg);
    FileQueue fq = new FileQueue(Integer.parseInt(cfg.get("queue.size")));
    FileIndexer fi = new FileIndexer(fq);

    /* print the directory structure */
    fs.show();

    /* create as many crawlers as there are directories under the root */
    ArrayList<FileCrawler> crawlers = new ArrayList<FileCrawler>();
    for (FileSystemElement element: fs.getRoot().getChildren()) {
      if (element instanceof Directory) {
        Directory dir = (Directory) element;
        crawlers.add(new FileCrawler(dir, fq));
      } else if (element instanceof File) {
        fq.put((File) element);
      }
    }

    /* create a list of threads to add indexers and crawlers to */
    ArrayList<Thread> threads = new ArrayList<Thread>();

    /* add file crawlers to the list of threads */
    for (int i = 0; i < crawlers.size(); i++) {
      threads.add(new Thread(crawlers.get(i)));
    }

    /* add file indexers to the list of threads */
    for (int i = 0; i < Integer.parseInt(cfg.get("indexer.number")); i++) {
      threads.add(new Thread(fi));
    }

    /* start all the threads */
    for (Thread t: threads) {
      t.start();
    }

    /* wait as long as main.runtime for threads to run */
    try {
      Thread.sleep(Integer.parseInt(cfg.get("main.runtime")));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    /* kill all the threads */
    for (Thread t: threads) {
      t.interrupt();
    }

    /* end of the main method */
  }

  /**
   * This class must not be instantiated.
   */
  private FileSystemMain() {
  }

}
