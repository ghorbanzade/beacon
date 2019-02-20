//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw14;

import java.util.EventListener;

/**
* As part of the multicast design pattern, this interface
* extends event listener and introduces an abstract method
* to observe stock-specific observable. The abstract method
* is implemented by observers that can display stock
* information.
*
* @author Pejman Ghorbanzade
* @see StockEventObservable
*/
public interface StockEventObserver extends EventListener {
  /**
  * All classes implementing this interface must implement the
  * update stock method. This allows the observable to directly
  * invoke updateStock without the need to typecast the object,
  * when stock information of the observable should be updated.
  *
  * @param observable the observable object whose data are of
  *        concern.
  */
  public void updateStock(StockEventObservable observable);
}
