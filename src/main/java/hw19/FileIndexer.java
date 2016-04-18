//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

/**
 * This class defines a file indexer as an object that reads files
 * from a file queue every certain amount of time.
 *
 * @author Pejman Ghorbanzade
 * @see FileQueue
 * @see File
 */
public final class FileIndexer implements Runnable {

  /**
   * A file indexer takes account of the file queue it should read from
   * and the time it should wait before every read.
   */
  private final FileQueue fq;
  private final int speed;

  /**
   * Upon construction, a file indexer loads the configuration file to
   * determine the time it should wait between every indexing action.
   *
   * @param fq the file queue from which files should be taken
   */
  public FileIndexer(FileQueue fq) {
    ConfigReader cr = new ConfigReader("/filesystem.properties");
    this.speed = Integer.parseInt(cr.get("indexer.speed"));
    this.fq = fq;
  }

  /**
   * Following the problem description, the file indexer does nothing
   * to a file other than printing its information on the output.
   *
   * @param file the file to be indexed
   */
  private void indexFile(File file) {
    if (file != null) {
      System.out.printf("indexing %s%n", file);
    }
  }

  /**
   * An indexer thread continues to read a file from the file queue as
   * long as it is not interrupted by the main thread. Every time a file
   * is indexed, the indexer will wait for a period specified in the
   * configuration file.
   */
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        this.indexFile(this.fq.get());
        Thread.sleep(this.speed);
      } catch (InterruptedException e) {
        System.out.printf("file indexer stopped by the main thread%n");
        Thread.currentThread().interrupt();
      }
    }
  }

}
