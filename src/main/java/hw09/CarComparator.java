//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw09;

import java.io.Serializable;
import java.util.Comparator;

/**
* This class defines a parent class for different comparators used to compare
* cars. By implementing the Comparator interface, this class ensures that any
* comparator used for cars will override the compare method. In addition,
* this class ensures that every comparator will have a name.
*
* @author       Pejman Ghorbanzade
* @see          Car
*/
public abstract class CarComparator<T extends Car> implements Comparator<T>, Serializable {
  /**
  * Every comparator used to compare two cars has a name.
  */
  private final String name;

  /**
  * The constructor will initialize the name of comparators defined for Cars.
  *
  * @param name of the comparator
  */
  public CarComparator(String name) {
    this.name = name;
  }

  /**
  * This getter method gives access to the name of the comparator.
  *
  * @return name of the comparator
  */
  public String getName() {
    return this.name;
  }

  /**
  * Each comparator class that inherits this class must implement and override
  * the compare method of Comperator interface.
  *
  * @param car1 the car we are comparing against
  * @param car2 the car we are comparing with
  */
  @Override
  public abstract int compare(Car car1, Car car2);
}
