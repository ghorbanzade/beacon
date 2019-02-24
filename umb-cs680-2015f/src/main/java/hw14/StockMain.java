
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw14;

import java.util.Timer;

/**
* This class presents the main method of the program to
* demonstrate how the observer design pattern works for this
* problem.
*
* @author Pejman Ghorbanzade
* @see StockEventObservable
*/
public final class StockMain {
  /**
  * This program creates a stock market as an observable object
  * with three observer classes watching its data. The program
  * uses a timer to refresh stock data and display updated data
  * in observer classes, using the multicast design pattern.
  *
  * @param args command line arguments given to this program
  */
  public static void main(String[] args) {
    StockEventObservable market = new StockEventObservable();
    market.addEventListener(new TextObserver());
    market.addEventListener(new TableObserver());
    market.addEventListener(new PieChartObserver());
    long refreshRate = Long.parseLong(StockConfig.getInstance()
        .get("rate.refresh"));
    Timer timer = new Timer();
    timer.schedule(new StockQuoteRefreshTask(timer, market), 0, refreshRate);
  }

  /**
  * This class must not be instantiated.
  */
  private StockMain() {
    // intentionally left empty.
  }
}
