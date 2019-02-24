//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw23;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class represents an observable object, or data in the model-view
 * paradigm. It can be subclassed to represent an object that the application
 * wants to have observed.
 *
 * @author Pejman Ghorbanzade
 * @see Observer
 */
public class Observable {

  private final ArrayList<Observer> observers;
  private final ReentrantReadWriteLock lock;
  private boolean changed;

  /**
   * Construct an Observable with zero Observers.
   */
  public Observable() {
    this.observers = new ArrayList<Observer>();
    this.lock = new ReentrantReadWriteLock();
    this.changed = false;
  }

  /**
   * Adds an observer to the set of observers for this object, provided that
   * it is not the same as some observer already in the set.
   *
   * @param obs an observer to be added
   */
  public void addObserver(Observer obs) throws NullPointerException {
    Objects.requireNonNull(obs);
    this.lock.writeLock().lock();
    try {
      this.observers.add(obs);
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * Deletes an observer from the set of observers of this object. Passing
   * null to this method will have no effect.
   *
   * @param obs the observer to be deleted
   */
  public void deleteObserver(Observer obs) {
    this.lock.writeLock().lock();
    try {
      this.observers.remove(obs);
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * If this object has changed, as indicated by the hasChanged method, then
   * notify all of its observers and then call the clearChanged method to
   * indicate that this object has no longer changed.
   */
  public void notifyObservers() {
    this.notifyObservers(null);
  }

  /**
   * If this object has changed, as indicated by the hasChanged method, then
   * notify all of its observers and then call the clearChanged method to
   * indicate that this object has no longer changed.
   *
   * <p>Each observer has its update method called with two arguments: this
   * observable object and the <pre>arg</pre> argument.
   *
   * @param obj any object
   */
  public void notifyObservers(Object obj) {
    for (Observer observer: observers) {
      observer.update(this, obj);
    }
    this.clearChanged();
  }

  /**
   * Marks this Observable object as having been changed.
   */
  protected void setChanged() {
    this.lock.writeLock().lock();
    try {
      this.changed = true;
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * Indicates that this object has no longer changed, or that it has already
   * notified all of its observers of its most recent change, so that the
   * hasChanged method will now return false. This method is called
   * automatically by the notifyObservers methods.
   */
  protected void clearChanged() {
    this.lock.writeLock().lock();
    try {
      this.changed = false;
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  /**
   * Tests if this object has changed.
   *
   * @return whether the object has changed since clearhanged has been called.
   */
  public boolean hasChanged() {
    this.lock.readLock().lock();
    try {
      return this.changed;
    } finally {
      this.lock.readLock().unlock();
    }
  }

  /**
   * Returns the number of observers of this Observable object.
   *
   * @return the number of observers of this object
   */
  public int countObservers() {
    this.lock.readLock().lock();
    try {
      return this.observers.size();
    } finally {
      this.lock.readLock().unlock();
    }
  }

}
