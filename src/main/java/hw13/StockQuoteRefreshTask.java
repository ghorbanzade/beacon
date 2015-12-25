//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.util.Timer;
import java.util.TimerTask;

/**
* This class extends TimerTask to define a task that can be
* executed continually after a specific amount of time, as
* long as the total execution time of the task is less than
* the time specified for runtime duration of the program.
*
* @author Pejman Ghorbanzade
* @StockMain
*/
public final class StockQuoteRefreshTask extends TimerTask {
  /**
  * Each task keeps track of the time it is initiated, the duration 
  * should be executed, the timer that has scheduled the task and
  * the observable instance to be updated at each run.
  */
  private StockQuoteObservable market;
  private Timer timer;
  private long tic;
  private long runtime;

  /**
  * Each instance of this class needs to know the timer that scheduled
  * the task (so it can stop it) and the observable instance the task
  * should update.
  *
  * @param timer the timer instance that has scheduled this task
  * @param market an instance of the stock market
  */
  public StockQuoteRefreshTask(Timer timer, StockQuoteObservable market) {
    this.timer = timer;
    this.market = market;
    this.tic = System.currentTimeMillis();
    this.runtime = Long.parseLong(StockConfig.getInstance()
        .get("runtime.program"));
  }

  /**
  * This method will update the observable stock quote intsance
  * with a new quote at each execution.
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
