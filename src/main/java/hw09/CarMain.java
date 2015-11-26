//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
* This class is provided to test our design by instantiating a number of
* cars and sorting them using different comparators.
*
* @author   Pejman Ghorbanzade
* @see      Car
* @see      CarComparator
*/
public final class CarMain {
  /**
  * Program's main method initializes a list of cars and sorts and prints the
  * list using different comparators as specified in the problem.
  *
  * @param args command line arguments given to program
  */
  public static void main(String[] args) {
    ArrayList<Car> cars = initializeCars();
    ArrayList<CarComparator> comparators = initializeComparators();
    for (CarComparator comparator: comparators) {
      printSortedList(cars, comparator);
    }
  }

  /**
  * This static method initializes a list of cars to be sorted. The
  * information used for this purpose are the ones given in lecture slide.
  *
  * @return a list of initialized Car objects
  */
  public static ArrayList<Car> initializeCars() {
    ArrayList<Car> cars = new ArrayList<Car>();
    cars.add(new Car(4880, 136636, 2000));
    cars.add(new Car(7995, 84297, 2001));
    cars.add(new Car(10697, 78321, 2003));
    cars.add(new Car(10900, 98362, 2002));
    cars.add(new Car(24995, 6822, 2007));
    return cars;
  }

  /**
  * This static method defines a list of comparators to be used to sort the
  * cars.
  *
  * @return a list of comperators to compare Car objects
  */
  public static ArrayList<CarComparator> initializeComparators() {
    ArrayList<CarComparator> comparators = new ArrayList<CarComparator>();
    comparators.add(new PriceComparator());
    comparators.add(new MileageComparator());
    comparators.add(new YearComparator());
    comparators.add(new ParetoComparator());
    return comparators;
  }

  /**
  * This static method takes a list of cars and a given comparator to sort
  * cars and then to print their information. This method handles the output
  * shown on the console.
  *
  * @param cars the list of cars to be sorted
  * @param comparator the comparator to be used to sort cars
  */
  public static void printSortedList(ArrayList<Car> cars, CarComparator comparator) {
    Collections.sort(cars, comparator);
    System.out.printf("Cars sorted using %s comparator:%n", comparator.getName());
    for (Car car: cars) {
      System.out.println(car);
    }
    System.out.println();
  }

  /**
  * This class is to test the design and must be allowed to be instantiated.
  */
  private CarMain() {
  }
}
