//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.util.Observer;
import java.util.Timer;

/**
* This class presents the main method of the program to
* demonstrate how the observer design pattern works for this
* problem.
*
* @author Pejman Ghorbanzade
* @see StockQuoteObservable
*/
public final class StockMain {
  /**
  * This program creates a stock market as an observable object
  * with three observer classes watching its data. The program
  * uses a timer to refresh stock data and display updated data
  * in observer classes, using the observer design pattern.
  *
  * @param args command line arguments given to this program
  */
  public static void main(String[] args) {
    StockQuoteObservable market = new StockQuoteObservable();
    market.addObserver(new TextObserver());
    market.addObserver(new TableObserver());
    market.addObserver(new PieChartObserver());
    long refreshRate = Long.parseLong(StockConfig.getInstance()
        .get("rate.refresh"));
    Timer timer = new Timer();
    timer.schedule(new StockQuoteRefreshTask(timer, market), 0, refreshRate);
  }
}
