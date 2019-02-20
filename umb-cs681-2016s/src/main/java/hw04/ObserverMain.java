//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw04;

/**
 * This class demonstrate how our design works by creating an observer in
 * form of a lambda expression implementing the observer interface.
 *
 * @author Pejman Ghorbanzade
 * @see Observable
 * @see Observer
 */
public final class ObserverMain {
  /**
   * The main method verifies the design by creating an observable with one
   * observer object of an anonymous class declared as a lambda expression.
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    Observable observable = new Observable();
    observable.addObserver( (Observable obs, Object obj)-> {
      System.out.println(obj);
    });
    observable.setChanged();
    observable.notifyObservers("Hi!");
  }

  /**
   * This class must not be instantiated.
   */
  private ObserverMain() {
  }
}
