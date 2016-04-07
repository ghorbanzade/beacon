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
public final class FileCrawler implements Runnable {

  /**
   *
   */
  private FileQueue fq;
  private Directory root;

  /**
   *
   */
  public FileCrawler(Directory root, FileQueue fq) {
    this.fq = fq;
    this.root = root;
  }

  /**
   *
   */
  private void crawl(Directory root) {
    for (FSElement element: root.getChildren()) {
      if (element instanceof Directory) {
        this.crawl((Directory) element);
      } else if (element instanceof File) {
        File file = (File) element;
        while (true) {
          if (this.fq.isFull()) {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
              Thread.currentThread().interrupt();
            }
          } else {
            this.fq.put(file);
          }
        }
      }
    }
  }

  /**
   *
   */
  public void run() {
    this.crawl(this.root);
  }

}
