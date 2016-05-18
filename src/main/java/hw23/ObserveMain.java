//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw23;

/**
 * Demonstrates how a thread-safe observable can be passed an implementation
 * of a the observer functional interface.
 *
 * @author Pejman Ghorbanzade
 * @see Observable
 * @see Observer
 */
public final class ObserveMain {

  /**
   * This program creates an instance of a thread-safe observable and passes
   * an implementation of the observer to it which prints hello once the
   * observable updates its observers.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    Observable observable = new Observable();
    observable.addObserver((Observable obs, Object obj)-> {
      System.out.println("Hello");
    });
    observable.notifyObservers();
  }

  /**
   * This class must not be instantiated.
   */
  private ObserveMain() {
  }

}
