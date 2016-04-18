//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A file queue class defines the queue in which files read by the
 * crawler should be put and from which indexers should take files.
 * A file queue is threadsafe.
 *
 * @author Pejman Ghorbanzade
 * @see FileCrawler
 * @see FileIndexer
 * @see File
 */
public final class FileQueue {

  /**
   * A file queue uses an arraylist to hold the files. The size of the
   * arraylist should not exceed a given threshold. The filequeue uses
   * a lock to allow thread-safe functionality.
   */
  private final int threshold;
  private final ArrayList<File> files;
  private final ReentrantLock lock;
  private boolean flag = false;

  /**
   * A file queue is simply defined as a list of files whose size should
   * not exceed a given threshold.
   *
   * @param threshold the maximum number of files to be put on the queue
   */
  public FileQueue(int threshold) {
    this.threshold = threshold;
    this.files = new ArrayList<File>();
    this.lock = new ReentrantLock();
  }

  /**
   * This method enqueues one file to the file queue. It is designed to be
   * called by the crawler. It is crawler's responsibility to check if the
   * queue is not full before trying adding to the queue.
   *
   * @param file the file to put on the queue
   */
  public void put(File file) {
    this.lock.lock();
    try {
      this.files.add(file);
    } finally {
      this.lock.unlock();
    }
  }

  /**
   * This method dequeues one file from the file queue and returns it to
   * the indexer.
   *
   * @return a file that was on the queue or null if the queue was empty
   */
  public File get() {
    while (true) {
      this.lock.lock();
      try {
        if (this.files.isEmpty()) {
          break;
        }
        return this.files.remove(0);
      } finally {
        this.lock.unlock();
      }
    }
    return null;
  }

  /**
   * This method allows the crawler to check the queue size before trying to
   * put a new file in it.
   *
   * @return whether the file queue size is has reached the threshold or not
   */
  public boolean isFull() {
    while (true) {
      this.lock.lock();
      try {
        return this.threshold <= this.files.size();
      } finally {
        this.lock.unlock();
      }
    }
  }

  /**
   * This method is called by crawlers to check whether the main thread is
   * asking them to stop.
   *
   * @return whether the flag to stop crawlers is set
   */
  public boolean getFlag() {
    while (true) {
      this.lock.lock();
      try {
        return this.flag;
      } finally {
        this.lock.unlock();
      }
    }
  }

  /**
   * This method is called by the main thread to signal to all crawlers that
   * it is time tot stop.
   */
  public void setFlag() {
    while (true) {
      this.lock.lock();
      try {
        this.flag = true;
        break;
      } finally {
        this.lock.unlock();
      }
    }
  }

}
