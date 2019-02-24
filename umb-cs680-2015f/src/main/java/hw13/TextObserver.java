//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw13;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
* This class defines TextObserver as one of the several observers
* that can display stock information stored in an instance of
* StockQuoteObservable.
*
* @author Pejman Ghorbanzade
* @see StockQuoteObservable
*/
public final class TextObserver implements Observer {
  /**
  * This class has a default constructor.
  */
  public TextObserver() {
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
      this.display(obs.getEvents());
    } else {
      throw new UnsupportedOperationException("Observable not supported");
    }
  }

  /**
  * This helper method will take care of how a given set of stock
  * events should be displayed on the console, in the Text observer.
  *
  * @param events the list of stock events to be displayed
  */
  public void display(ArrayList<StockEvent> events) {
    System.out.println("Text Observer:");
    for (StockEvent event: events) {
      System.out.printf("  %s: %.2f%n", event.getTrademark().getSymbol(), event.getQuote());
    }
  }
}
