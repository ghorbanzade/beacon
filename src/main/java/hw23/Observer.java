//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw23;

/**
 * A class can implement the Observer interface when it wants to be informed
 * of changes in observable objects.
 *
 * @author Pejman Ghorbanzade
 * @see Observable
 */
@FunctionalInterface
public interface Observer {

  /**
   * This method is called whenever the observed object is changed. An
   * application calls an Observable object's notifyObservers method to
   * have all the object's observers notified of the change.
   *
   * @param obs the observable object
   * @param obj an argument passed to the notifyObservers method
   */
  public abstract void update(Observable obs, Object obj);

}
