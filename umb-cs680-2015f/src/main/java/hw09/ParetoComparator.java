//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw09;

import java.util.ArrayList;

/**
* This class presents a method to compare two cars based on number of
* parameters in terms of which they are superior (better).
*
* @author   Pejman Ghorbanzade
* @see      CarComparator
* @see      Car
*/
public class ParetoComparator extends CarComparator  {
  /**
  * Type of any comparator object instantiated from this class will
  * be pareto.
  */
  public ParetoComparator() {
    super("pareto");
  }

  /**
  * Comparison rule for comparing two Car objects. In this comparator, a car
  * is considered as lower (better) if it dominates the other car in more
  * aspects.
  *
  * @param car1 the car we are comparing against
  * @param car2 the car we are comparing with
  */
  @Override
  public int compare(Car car1, Car car2) {
    ArrayList<CarComparator> comparators = new ArrayList<CarComparator>();
    comparators.add(new PriceComparator());
    comparators.add(new YearComparator());
    comparators.add(new MileageComparator());
    int sum = 0;
    for (CarComparator comparator: comparators) {
      sum += comparator.compare(car1, car2);
    }
    return sum;
  }
}
