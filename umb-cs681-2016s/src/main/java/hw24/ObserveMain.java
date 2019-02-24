//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw24;

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
      System.out.println(obj);
    });
    observable.setChanged();
    observable.notifyObservers("Hi");
  }

  /**
   * This class must not be instantiated.
   */
  private ObserveMain() {
  }

}
