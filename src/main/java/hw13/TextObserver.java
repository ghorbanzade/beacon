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
public final class TextObserver implements Observer {
  /**
  *
  */
  public TextObserver() {
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
    System.out.println("Text Observer:");
    for (StockEvent event: events) {
      System.out.printf("  %s: %.2f%n", event.getTrademark().getSymbol(), event.getQuote());
    }
  }
}
