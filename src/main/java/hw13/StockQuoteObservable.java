//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.util.ArrayList;
import java.util.Observable;

/**
* This class defines the observable component of the software
* design. The instance of Stock Quote Observable holds all stock
* information and updates its observers in case the information
* is updated.
*
* @author Pejman Ghorbanzade
* @see StockEvent
*/
public final class StockQuoteObservable extends Observable {
  /**
  * Each stock quote observable instance has a list of stock
  * events. Observers of the instance can access the list for
  * presentation purposes.
  */
  private ArrayList<StockEvent> events = new ArrayList<StockEvent>();

  /**
  * The constructor of stock quote observable calls the static
  * method of StockFileHandler to load stock events already given
  * in the stock information file.
  */
  public StockQuoteObservable() {
    this.events.addAll(StockFileHandler.loadEvents());
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
  * This method updates the quote of a stock event whose symbol is
  * given. In case no stock event with specified symbol exists,
  * the method will throw an unsupported operation exception.
  * Note that updating an event will not trigger notifyObservers
  * method.
  *
  * @param symbol the symbol of the stock event with updated quote
  * @param quote the new quote of the stock event whose symbol is
  *        given
  */
  public void updateEvent(String symbol, float quote)
      throws UnsupportedOperationException {
    StockEvent event = new StockEvent(new StockTrademark(symbol), 0);
    int index = this.events.indexOf(event);
    if (index == -1) {
      throw new UnsupportedOperationException("Event not found.");
    } else {
      this.events.get(index).setQuote(quote);
      setChanged();
    }
  }

  /**
  * This accessor method will return the stock events already
  * added to the stoke quote observable instance.
  *
  * @return a list of StockEvent objects known to the market
  */
  public ArrayList<StockEvent> getEvents() {
    return this.events;
  }
}
