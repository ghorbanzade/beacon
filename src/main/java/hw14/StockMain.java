
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

import java.util.Timer;

public final class StockMain {
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
}
