//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
* This class defines PieChartObserver as one of the several
* observers that can display stock information stored in an
* instance of StockQuoteObservable.
*
* @author Pejman Ghorbanzade
* @see StockQuoteObservable
*/
public final class PieChartObserver implements Observer {
  /**
  * This class has a default constructor.
  */
  public PieChartObserver() {
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
      StockQuoteObservable market = (StockQuoteObservable) observable;
      this.display(market.getEvents());
    } else {
      throw new UnsupportedOperationException("Observable not supported");
    }
  }

  /**
  * This helper method will take care of how a given set of stock events
  * should be displayed on the console, in the PieChart observer.
  *
  * @param events the list of stock events to be displayed
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
