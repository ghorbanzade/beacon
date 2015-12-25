
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

import java.util.ArrayList;

public final class StockEventObservable {
  /**
  *
  */
  private ArrayList<StockEventObserver> observers =
      new ArrayList<StockEventObserver>();
  private ArrayList<StockEvent> events = new ArrayList<StockEvent>();

  /**
  *
  */
  public StockEventObservable() {
    this.events.addAll(StockFileHandler.loadEvents());
  }

  /**
  *
  *
  *
  */
  public void addEventListener(StockEventObserver observer) {
    this.observers.add(observer);
  }

  /**
  *
  *
  * @param event the new StockEvent to be registered
  */
  public void addEvent(StockEvent event) {
    this.events.add(event);
  }

  /**
  *
  *
  * @return a list of StockEvent objects known to the market
  */
  public ArrayList<StockEvent> getEvents() {
    return this.events;
  }

  /**
  *
  *
  * @param event the event with updated quote
  */
  public void updateEvent(String symbol, float quote) {
    StockEvent event = new StockEvent(new StockTrademark(symbol), 0);
    int index = this.events.indexOf(event);
    if (index == -1) {
      throw new UnsupportedOperationException("Event not found.");
    } else {
      this.events.get(index).setQuote(quote);
    }
  }

  /**
  *
  *
  *
  */
  public void notifyObservers() {
    for (StockEventObserver observer: this.observers) {
      observer.updateStock(this);
    }
  }
}
