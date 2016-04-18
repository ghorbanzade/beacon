//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

/**
 *
 *
 * @author Pejman Ghorbanzade
 * @see FileQueue
 * @see File
 */
public final class FileCrawler implements Runnable {

  /**
   *
   */
  private final FileQueue fq;
  private final Directory root;
  private final int speed;

  /**
   *
   *
   * @param root
   * @param fq
   */
  public FileCrawler(Directory root, FileQueue fq) {
    ConfigReader cfg = new ConfigReader("/filesystem.properties");
    this.speed = Integer.parseInt(cfg.get("crawler.speed"));
    this.fq = fq;
    this.root = root;
  }

  /**
   *
   *
   * @param root top level directory whose elements should be crawled.
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
              Thread.sleep(this.speed);
            } catch (InterruptedException e) {
              e.printStackTrace();
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
   *
   */
  public void run() {
    this.crawl(this.root);
  }

}
