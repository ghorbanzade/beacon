//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

public final class TextObserver implements StockEventObserver {
  public TextObserver() {
  }

  @Override
  public void updateStock(StockEventObservable observable) {
    this.display(observable);
  }

  public void display(StockEventObservable observable) {
    System.out.println("Text Observer:");
    for (StockEvent event: observable.getEvents()) {
      System.out.printf("  %s: %.2f%n", event.getTrademark().getSymbol(), event.getQuote());
    }
  }
}
