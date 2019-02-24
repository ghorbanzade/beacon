//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw13;

import java.util.Observable;
import java.util.Observer;

/**
* This class defines TableObserver as one of the several observers
* that can display stock information stored in an instance of
* StockQuoteObservable.
*
* @author Pejman Ghorbanzade
* @see StockQuoteObservable
*/
public final class TableObserver implements Observer {
  /**
  * This class has a default constructor.
  */
  public TableObserver() {
    // intentionally left empty.
  }

  /**
  * This method is called whenever the observable object is updated.
  *
  * @param observable the object whose data are of concern
  * @param arg the argument passed to the notifyObservers method
  */
  @Override
  public void update(Observable observable, Object arg) {
    if (observable instanceof StockQuoteObservable) {
      StockQuoteObservable obs = (StockQuoteObservable) observable;
      System.out.println("Table Observer:");
      System.out.printf(" %3s | %-6s | %-10s | %-6s%n", "Row", "Symbol", "Name", "Quote");
      int row = 1;
      for (StockEvent event: obs.getEvents()) {
        System.out.printf(" %3d | %-6s | %-10s | %-4.2f%n",
                          row++, event.getTrademark().getSymbol(),
                          event.getTrademark().getName(),
                          event.getQuote());
      }
    }
  }
}
