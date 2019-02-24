//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw14;

/**
* This class defines a stock event as a stock entry of a trademark
* with a specified quote.
*
* @author Pejman Ghorbanzade
* @see StockQuoteObservable
*/
public final class StockEvent {
  /**
  * Each stock event has a trademark and an actual quote.
  */
  private final StockTrademark mark;
  private float quote;

  /**
  * This constructor allows creating stock entries while
  * simultaneously specifying their quotes.
  *
  * @param mark trademark of the company associated with the stock
  *             entry
  * @param quote quote of the stock entry
  */
  public StockEvent(StockTrademark mark, float quote) {
    this.mark = mark;
    this.quote = quote;
  }

  /**
  * This accessor method returns the trademark instance of a stock
  * event. This trademark instance may in turn be used to print
  * the name and symbol of an event.
  *
  * @return the trademark instance of a stock entry
  */
  public StockTrademark getTrademark() {
    return this.mark;
  }

  /**
  * This accessor method returns the actual quote of the stock
  * event.
  *
  * @return current quote of the stock event
  */
  public float getQuote() {
    return this.quote;
  }

  /**
  * This mutator method allows updating quote of a stock event
  * object.
  *
  * @param quote the new quote of the stock event
  */
  public void setQuote(float quote) {
    this.quote = quote;
  }

  /**
  * To compare two stock events, equals method has been overriden
  * so that stock events be compared for equality based on their
  * trademarks and not on their quotes.
  *
  * @param obj the object to be checked for equality
  * @return whether the event matches another given event
  */
  @Override
  public boolean equals(Object obj) {
    boolean out = false;
    if (obj instanceof StockEvent) {
      StockEvent event = (StockEvent) obj;
      out = this.getTrademark().equals(event.getTrademark());
    }
    return out;
  }

  /**
  * Since equals method has been overriden, this method overrides
  * the hashCode method of stock event to allow possiblity of
  * using stock events in a hashmap (although not used in this
  * program). Bassd on current implementatoin, hashcode is
  * generated based on the trademark of a stock event.
  *
  * @return hashcode of trademark of the event instance
  */
  @Override
  public int hashCode() {
    return this.getTrademark().hashCode();
  }
}
