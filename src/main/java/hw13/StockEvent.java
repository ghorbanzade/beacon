
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

/**
*
* @author Pejman Ghorbanzade
*/
public final class StockEvent {
  /**
  *
  */
  private StockTrademark mark;
  private float quote;

  /**
  *
  *
  * @param mark
  * @param quote
  */
  public StockEvent(StockTrademark mark, float quote) {
    this.mark = mark;
    this.quote = quote;
  }

  /**
  *
  *
  * @return
  */
  public StockTrademark getTrademark() {
    return this.mark;
  }

  /**
  *
  *
  * @return
  */
  public float getQuote() {
    return this.quote;
  }

  /**
  *
  *
  *
  */
  public void setQuote(float quote) {
    this.quote = quote;
  }

  /**
  *
  *
  * return whether the event matches a given event
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
  *
  *
  * @return hashcode of the symbol of the event instance
  */
  @Override
  public int hashCode() {
    return this.getTrademark().hashCode();
  }
}
