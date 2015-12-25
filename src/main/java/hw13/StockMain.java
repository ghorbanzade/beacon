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
*
*
* @author Pejman Ghorbanzade
* @see StockQuoteObservable
*/
public final class StockMain {
  public static void main(String[] args) {
    long refreshRate = Long.parseLong(StockConfig.getInstance()
        .get("rate.refresh"));
    StockQuoteObservable market = new StockQuoteObservable();
    market.addObserver(new TextObserver());
    market.addObserver(new TableObserver());
    market.addObserver(new PieChartObserver());
    Timer timer = new Timer();
    timer.schedule(new StockQuoteRefreshTask(timer, market), 0, refreshRate);
  }
}
