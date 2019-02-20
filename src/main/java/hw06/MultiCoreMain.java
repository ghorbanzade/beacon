//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw06;

import java.util.ArrayList;

/**
 * This program demonstrates how using multiple threads will improve
 * computional performance.
 *
 * @author Pejman Ghorbanzade
 */
public class MultiCoreMain {

  /**
   * This program takes two command line arguments, first of which is the
   * number of multiplication operations and the latter the number of threads
   * to be used.
   *
   * @param args command line arguments given to this program
   */
  public static void main(String[] args) throws Exception {
    final long tic = System.nanoTime();
    ArrayList<Thread> threads = new ArrayList<Thread>();
    if (args.length < 2) {
      System.out.printf("missing command line arguments%n");
      return;
    }
    final long numTimes = Long.parseLong(args[0]);
    final int numThreads = Integer.parseInt(args[1]);
    for (int i = 0; i < numThreads; i++) {
      Thread thread = new Thread(
          ()-> {
            int num = 25;
            for (long j = 0; j < numTimes; j++) {
              num *= 25;
            }
          });
      threads.add(thread);
      thread.start();
    }
    for (Thread t: threads) {
      t.join();
    }
    final long toc = System.nanoTime();
    final double elapsed = (toc - tic) / 1e9;
    System.out.printf("elapsed time: %.6f seconds.%n", elapsed);
  }

}
