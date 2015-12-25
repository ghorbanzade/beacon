
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

import java.util.ArrayList;

/**
* This class defines the observable component of the software
* design. The instance of stock event observable holds all stock
* information and notifies its observers in case the information
* is updated.
*
* @author Pejman Ghorbanzade
* @see StockEvent
*/
public final class StockEventObservable {
  /**
  * Each stock observable instance has a list of stock events and
  * a list of observers watching those events.
  */
  private ArrayList<StockEventObserver> observers =
      new ArrayList<StockEventObserver>();
  private ArrayList<StockEvent> events = new ArrayList<StockEvent>();

  /**
  * The constructor of stock observable calls the static method
  * of StockFileHandler to load stock events already given in the
  * stock information file.
  */
  public StockEventObservable() {
    this.events.addAll(StockFileHandler.loadEvents());
  }

  /**
  * Since java.util.Observer is not extended in this problem,
  * this method is defined to add observers for the observable
  * instance.
  */
  public void addEventListener(StockEventObserver observer) {
    this.observers.add(observer);
  }

  /**
  * This method allows adding new stock events to the observable
  * instance.
  *
  * @param event the new StockEvent to be registered
  */
  public void addEvent(StockEvent event) {
    this.events.add(event);
  }

  /**
  * This accessor method will return the stock events already
  * added to the stoke event observable instance.
  *
  * @return a list of StockEvent objects known to the market
  */
  public ArrayList<StockEvent> getEvents() {
    return this.events;
  }

  /**
  * This method updates the quote of a stock event whose symbol
  * is given. In case no stock event with specified symbol exists,
  * the method will throw an unsupported operation exception.
  * Note that updating an event will not trigger notifyObservers
  * method.                                                      
  *
  * @param symbol the symbol of the stock event with updated quote
  * @param quote the new quote of the stock event whose symbol is
  *        given
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
  * Since java.util.Observer is not extended in this problem,
  * this method is defined and declared as public so that clients
  * can manually notify the observers of the observable instance.
  */
  public void notifyObservers() {
    for (StockEventObserver observer: this.observers) {
      observer.updateStock(this);
    }
  }
}
