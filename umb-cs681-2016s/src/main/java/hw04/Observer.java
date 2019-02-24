//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw04;

/**
 * This functional interface is implemented by any (anonymous) class that
 * declares what should happen in case the observable object is changed.
 *
 * @author Pejman Ghorbanzade
 * @see Observable
 */
@FunctionalInterface
public interface Observer {
  /**
   * This method is called whenever the observed object is changed.
   * An application calls an Observable object's notifyObservers method to
   * have all the object's observers notified of the change.
   *
   * @param observable the observable object
   * @param arg an argument passed to the notifyObservers method
   */
  public abstract void update(Observable observable, Object arg);
}
