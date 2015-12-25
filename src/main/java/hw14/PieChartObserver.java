//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

public final class PieChartObserver implements StockEventObserver {
  public PieChartObserver() {
  }

  @Override
  public void updateStock(StockEventObservable observable) {
    this.display(observable);
  }

  public void display(StockEventObservable observable) {
    System.out.println("Pie Chart Observer:");
    float sum = 0;
    for (StockEvent event: observable.getEvents()) {
      sum += event.getQuote();
    }
    for (StockEvent event: observable.getEvents()) {
      float value = event.getQuote() / sum * 100F;
      System.out.printf("  %s: %.2f%%%n",
          event.getTrademark().getSymbol(), value);
    }
  }
}
