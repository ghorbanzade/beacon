//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

/**
* This class defines PieChartObserver as one of the several
* observers that can display stock information stored in an
* instance of StockEventObservable.
*
* @author Pejman Ghorbanzade
* @see StockEventObservable
*/
public final class PieChartObserver implements StockEventObserver {
  /**
  * This class has a default constructor.
  */
  public PieChartObserver() {
    // intentionally left empty.
  }

  /**
  * This method is called whenever the notifyObservers method of
  * the observable object is invoked.
  *
  * @param observable the object whose data are of concern
  */
  @Override
  public void updateStock(StockEventObservable observable) {
    this.display(observable);
  }

  /**
  * This helper method will take care of how the stock information
  * stored in an observable object should be displayed on the
  * console, in the PieChart observer.
  *
  * @param observable the observable object that contains stock
  *        information
  */
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
