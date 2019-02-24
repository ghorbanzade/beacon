//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents an observable object, or data in the model-view
 * paradigm.
 *
 * @author Pejman Ghorbanzade
 * @see Observer
 */
public class Observable {

  /**
   * Each observable object has a list of observers and a flag that indicates
   * whether it has changed since the last update to the observers.
   */
  private boolean changed = false;
  private final ArrayList<Observer> obs;

  /**
   * Construct an Observable with zero Observers.
   */
  public Observable() {
    this.obs = new ArrayList<Observer>();
  }

  /**
   * Adds an observer to the set of observers for this object, provided that it
   * is not the same as some observer already in the set. The order in which
   * notifications will be delivered to multiple observers is not specified.
   *
   * @param observer the observer to be added
   */
  public void addObserver(Observer observer) {
    Objects.requireNonNull(observer);
    if (!this.obs.contains(observer)) {
      this.obs.add(observer);
    }
  }

  /**
   * Clears the observer list so that this object no longer has any observers.
   *
   * @param observer the observer to be removed from the list of observers
   */
  public void deleteObserver(Observer observer) {
    this.obs.remove(observer);
  }

  /**
   * Marks this Observable object as having been changed; the hasChanged
   * method will now return true.
   */
  protected void setChanged() {
    this.changed = true;
  }

  /**
   * Indicates that this object has no longer changed, or that it has already
   * notified all of its observers of its most recent change, so that the
   * hasChanged method will now return false. This method is called
   * automatically by the notifyObservers methods.
   */
  protected void clearChanged() {
    this.changed = false;
  }

  /**
   * Tests if this object has changed.
   *
   * @return whether the observable has changed
   */
  public boolean hasChanged() {
    return this.changed;
  }

  /**
   * If this object has changed, as indicated by the hasChanged method, then
   * notify all of its observers and then call the clearChanged method to
   * indicate that this object has no longer changed.
   *
   * <p>Each observer has its update method called with two arguments: this
   * observable object and the arg argument.
   *
   * @param arg the argument with which to update the observers
   */
  public void notifyObservers(Object arg) {
    for (int i = 0; i < this.obs.size(); i++) {
      this.obs.get(i).update(this, arg);
    }
  }
}
