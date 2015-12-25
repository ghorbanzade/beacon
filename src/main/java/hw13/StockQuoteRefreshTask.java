
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.util.Timer;
import java.util.TimerTask;

/**
*
* @author Pejman Ghorbanzade
*/
public final class StockQuoteRefreshTask extends TimerTask {
  /**
  *
  *
  *
  */
  private StockQuoteObservable market;
  private Timer timer;
  private long tic;
  private long runtime;

  /**
  *
  *
  *
  */
  public StockQuoteRefreshTask(Timer timer, StockQuoteObservable market) {
    this.timer = timer;
    this.market = market;
    this.tic = System.currentTimeMillis();
    this.runtime = Long.parseLong(StockConfig.getInstance()
        .get("runtime.program"));
  }

  /**
  *
  *
  *
  */
  @Override
  public void run() {
    if (scheduledExecutionTime() - tic < runtime) {
      for (StockEvent event: this.market.getEvents()) {
        float coeff = (float) (1 + (Math.random() - 0.5) / 5);
        float quote = coeff * event.getQuote();
        this.market.updateEvent(event.getTrademark().getSymbol(), quote);
      }
    } else {
      this.timer.cancel();
    }
  }
}
