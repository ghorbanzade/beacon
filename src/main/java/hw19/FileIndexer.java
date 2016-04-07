//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

/**
 *
 */
public final class FileIndexer implements Runnable {

  /**
   *
   */
  private FileQueue fq;

  /**
   *
   */
  public FileIndexer(FileQueue fq) {
    this.fq = fq;
  }

  /**
   *
   */
  private void indexFile(File file) {
    if (file != null) {
      System.out.printf("indexing %s%n", file);
    }
  }

  /**
   *
   */
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        this.indexFile(this.fq.get());
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
  }

}
