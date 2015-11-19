//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw09;

import java.util.Comparator;

/**
* This class presents a method to compare two cars based on their estimated
* price.
*
* @author   Pejman Ghorbanzade
* @see      CarComparator
* @see      Car
*/
public class PriceComparator extends CarComparator {
  /**
  * Type of any comparator object instantiated from this class will
  * be pareto.
  */
  public PriceComparator() {
    super("price");
  }

  /**
  * Comparison rule for comparing two Car objects. In this comparator, a car
  * is considered as lower (better) if it is cheaper.
  *
  * @param car1 the car we are comparing against
  * @param car2 the car we are comparing with
  */
  @Override
  public int compare(Car car1, Car car2) {
    return car1.getPrice() - car2.getPrice();
  }
}
