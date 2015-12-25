//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.util.Observable;
import java.util.Observer;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class TableObserver implements Observer {
  /**
  *
  */
  public TableObserver() {
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
      System.out.println("Table Observer:");
      System.out.printf(" %3s | %-6s | %-10s | %-6s%n", "Row", "Symbol", "Name", "Quote");
      int i = 1;
      for (StockEvent event: obs.getEvents()) {
        System.out.printf(" %3d | %-6s | %-10s | %-4.2f%n",
                          i++, event.getTrademark().getSymbol(),
                          event.getTrademark().getName(),
                          event.getQuote());
      }
    }
  }
}
