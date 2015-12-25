//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class PieChartObserver implements Observer {
  /**
  *
  */
  public PieChartObserver() {
  }

  /**
  *
  *
  * @param observable
  * @param arg
  */
  @Override
  public void update(Observable observable, Object arg) {
    if (observable instanceof StockQuoteObservable) {
      StockQuoteObservable obs = (StockQuoteObservable) observable;
      this.display(obs.getEvents());
    } else {
      throw new UnsupportedOperationException("Observable not supported");
    }
  }

  /**
  *
  *
  * @param events
  */
  public void display(ArrayList<StockEvent> events) {
    System.out.println("Pie Chart Observer:");
    float sum = 0;
    for (StockEvent event: events) {
      sum += event.getQuote();
    }
    for (StockEvent event: events) {
      float value = event.getQuote() / sum * 100F;
      System.out.printf("  %s: %.2f%%%n", event.getTrademark().getSymbol(), value);
    }
  }
}
