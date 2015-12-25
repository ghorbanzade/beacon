//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

public final class TableObserver implements StockEventObserver {
  public TableObserver() {
  }

  @Override
  public void updateStock(StockEventObservable observable) {
    this.display(observable);
  }

  public void display(StockEventObservable observable) {
    System.out.println("Table Observer:");
    System.out.printf(" %3s | %-6s | %-10s | %-6s%n", "Row", "Symbol", "Name", "Quote");
    int row = 1;
    for (StockEvent event: observable.getEvents()) {
      System.out.printf(" %3d | %-6s | %-10s | %-4.2f%n",
          row++, event.getTrademark().getSymbol(),
          event.getTrademark().getName(),
          event.getQuote());
    }
  }
}
