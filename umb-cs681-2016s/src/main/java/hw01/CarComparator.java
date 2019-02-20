//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw01;

import java.util.Comparator;

/**
 * This class allows us to assign a name to comparator objects and specify
 * whether they sort cars in ascending or descending order. The information
 * is used for printing purposes only.
 *
 * @author Pejman Ghorbanzade
 * @see Car
 */
public class CarComparator {

  /**
   * An object of this class is assigned a name and describes the comparing
   * logic and whether it sorts the list in ascending or descending order.
   */
  private final String name;
  private final boolean isReverse;
  private final Comparator<Car> comparator;

  /**
   * The constructor for this class takes the logic of the comparator as a
   * comparator object, a name to assign to it and whether it sorts cars
   * in ascending or descending order.
   *
   * @param name the name of the comparator as we want to be printed
   * @param comp the comparator that describes the logic of sorting
   * @param rev whether the comparator should work in reverse order
   */
  public CarComparator(String name, Comparator<Car> comp, boolean rev) {
    this.name = name;
    this.isReverse = rev;
    this.comparator = rev ? comp.reversed() : comp;
  }

  /**
   * This getter method returns the name of the comparator for printing
   * purposes.
   *
   * @return name assigned to the comparator
   */
  public String getName() {
    return this.name;
  }

  /**
   * This getter method returns the comparator object that is handed to
   * sort method of collections class to sort the list of cars.
   *
   * @return the comparator object to be used for sorting
   */
  public Comparator<Car> getComparator() {
    return this.comparator;
  }

  /**
   * This getter method is used to print whether the list is sorted in
   * ascending or descending order.
   *
   * @return whether the comparator delivers the list in descending order
   */
  public boolean isReverse() {
    return this.isReverse;
  }
}
