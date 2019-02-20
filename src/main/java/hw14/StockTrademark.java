//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

/**
* This class defines trademarks of a stock entry which works like
* a key in the stock event object. This class is defined primarily
* for better code organization.
*
* @author Pejman Ghorbanzade
* @see StockEvent
*/
public final class StockTrademark {
  /**
  * Each trademark has a [unique] symbol and a name.
  */
  private String name;
  private final String symbol;

  /**
  * A trademark can be constructed simply by providing a symbol.
  * The constructor does not check whether a trademark of that
  * symbol has already been created. In any such case, the user
  * will not be able to add a stock event of the newly constructed
  * trademark to the stock observable.
  *
  * @param symbol the symbol of the new trademark is most likely
  *               an abbreviated form of the name of the trademark
  */
  public StockTrademark(String symbol) {
    this.symbol = symbol;
  }

  /**
  * This is the preferred way of instantiating trademarks. This
  * constructor allows creating new trademarks by specifying a
  * symbol and a name. Note that this constructor does not check
  * whether a trademark of specified symbol has already been
  * created.
  *
  * @param symbol the symbol of the new trademark is most likely
  *               an abbreviated form of the name of the trademark
  * @param name the name of the new trademark is most likely the
  *             name of the company holding the trademark
  */
  public StockTrademark(String symbol, String name) {
    this.name = name;
    this.symbol = symbol;
  }

  /**
  * This accessor method returns the name of a trademark object.
  * We assume that the name of a trademark would be the name of
  * the company holding that trademark.
  *
  * @return the name of a trademark object
  */
  public String getName() {
    return this.name;
  }

  /**
  * This accessor method would return the symbol of a trademark
  * object.
  *
  * @return the symbol of a trademark object.
  */
  public String getSymbol() {
    return this.symbol;
  }

  /**
  * A trademark is equal with another object in case the object
  * is a trademark and their symbols are the same.
  *
  * @param obj the object to be checked for equality
  * @return whether the trademark instance is equal to the given
  *         object.
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
  * Since equals method for this class has been overriden, this
  * method overrides hashcode method of the class such that 
  * objects of this class can be put in a hashmap (although not
  * used in this program). Based on current implementation,
  * hashcode of a trademark will be the hashcode of string literal
  * of its symbol.
  *
  * @return hashcode of the symbol of the trademark
  */
  @Override
  public int hashCode() {
    return this.getSymbol().hashCode();
  }
}
