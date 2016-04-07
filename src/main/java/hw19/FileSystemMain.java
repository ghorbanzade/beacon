//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;

/**
 *
 */
public final class FileSystemMain {

  /**
   *
   */
  public static void main(String[] args) {
    ConfigReader cfg = new ConfigReader("/filesystem.properties");
    int fsNum = Integer.parseInt(cfg.get("fselements.number"));
    int fiNum = Integer.parseInt(cfg.get("fileindexer.number"));
    FileNameReader fnr = new FileNameReader(cfg.get("filename.list"));

    FileQueue fq = new FileQueue(Integer.parseInt(cfg.get("filequeue.size")));
    FileSystem fs = initFileSystem();
    FileIndexer fi = new FileIndexer(fq);
    FileCrawler fc = new FileCrawler(fs.getRoot(), fq);

    fs.getRoot().showAllElements();

    ArrayList<FileCrawler> crawlers = new ArrayList<FileCrawler>();
    for (FSElement element: fs.getRoot().getChildren()) {
      if (element instanceof Directory) {
        Directory dir = (Directory) element;
        crawlers.add(new FileCrawler(dir, fq));
      } else if (element instanceof File) {
        fq.put((File) element);
      }
    }
    Thread[] fcThreads = new Thread[crawlers.size()];
    for (int i = 0; i < crawlers.size(); i++) {
      fcThreads[i] = new Thread(crawlers.get(i));
      fcThreads[i].start();
    }
    Thread[] fiThreads = new Thread[fiNum];
    for (int i = 0; i < fiNum; i++) {
      fiThreads[i] = new Thread(fi);
      fiThreads[i].start();
    }
    
  }

  /**
   *
   */
  public static FileSystem initFileSystem() {
    String user = "pejman";
    Directory dir1 = new Directory("pictures", user);
    File file1 = new File("e", user, 8);
    dir1.appendChild(file1);
    dir1.appendChild(new File("f", user, 28));
    dir1.appendChild(new Link("y", user, file1));
    Directory dir2 = new Directory("system", user);
    dir2.appendChild(new File("a", user, 256));
    dir2.appendChild(new File("b", user, 12));
    dir2.appendChild(new File("c", user, 64));
    Directory dir3 = new Directory("home", user);
    dir3.appendChild(new File("d", user, 56));
    dir3.appendChild(new Link("x", user, dir2));
    dir3.appendChild(dir1);
    FileSystem fs = FileSystem.getFileSystem();
    fs.getRoot().appendChild(dir2);
    fs.getRoot().appendChild(dir3);
    return fs;
  }

  /**
   * This class must not be instantiated.
   */
  private FileSystemMain() {
  }

}
