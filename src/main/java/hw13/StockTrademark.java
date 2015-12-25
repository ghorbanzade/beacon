//
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
public final class StockTrademark {
  /**
  *
  */
  private String name;
  private String symbol;

  /**
  *
  *
  * @param symbol
  */
  public StockTrademark(String symbol) {
    this.symbol = symbol;
  }

  /**
  *
  *
  * @param symbol
  * @param name
  */
  public StockTrademark(String symbol, String name) {
    this.name = name;
    this.symbol = symbol;
  }

  /**
  *
  *
  * @return
  */
  public String getName() {
    return this.name;
  }

  /**
  *
  *
  * @return
  */
  public String getSymbol() {
    return this.symbol;
  }

  /**
  *
  *
  *
  */
  @Override
  public boolean equals(Object obj) {
    boolean out = false;
    if (obj instanceof StockTrademark) {
      StockTrademark mark = (StockTrademark) obj;
      out = this.getSymbol().equals(mark.getSymbol());
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
    return this.getSymbol().hashCode();
  }
}
