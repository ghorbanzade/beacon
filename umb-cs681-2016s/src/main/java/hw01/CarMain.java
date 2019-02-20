//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class tests the design by instantiating a number of cars and
 * sorting them using different comparators.
 *
 * @author Pejman Ghorbanzade
 * @see Car
 * @see CarComparator
 * @see CarListReader
 */
public final class CarMain {

  /**
   * Program's main method initializes a list of cars and sorts and prints
   * the list using different comparators as specified in the problem.
   *
   * @param args command line arguments given to program
   */
  public static void main(String[] args) {
    CarListReader cr = new CarListReader("/cars.txt");
    ArrayList<Car> cars = cr.load();
    ArrayList<CarComparator> comps = initializeComparators();
    for (CarComparator comp: comps) {
      printList(cars, comp);
    }
  }

  /**
   * This method defines a list of comparators to be used to sort
   * the cars.
   *
   * @return an arraylist of car comparator objects
   */
  public static ArrayList<CarComparator> initializeComparators() {
    ArrayList<CarComparator> comps = new ArrayList<CarComparator>();
    comps.add(new CarComparator("price", compareByPrice(), false));
    comps.add(new CarComparator("mileage", compareByMileage(), false));
    comps.add(new CarComparator("year", compareByYear(), false));
    comps.add(new CarComparator("pareto", compareByPareto(), false));
    comps.add(new CarComparator("price", compareByPrice(), true));
    comps.add(new CarComparator("mileage", compareByMileage(), true));
    comps.add(new CarComparator("year", compareByYear(), true));
    comps.add(new CarComparator("pareto", compareByPareto(), true));
    return comps;
  }

  /**
   * This method takes a list of cars and a given comparator to sort
   * cars and then to print their information.
   * This method handles the output shown on the console.
   *
   * @param cars an arraylist of car objects
   * @param comparator the comparator to use to sort the car objects
   */
  public static void printList(ArrayList<Car> cars, CarComparator comparator) {
    Collections.sort(cars, comparator.getComparator());
    String order = comparator.isReverse() ? "descending" : "ascending";
    System.out.printf(
        "Cars sorted based on %s in %s order:%n",
        comparator.getName(),
        order
    );
    for (Car car: cars) {
      System.out.println(car);
    }
    System.out.println();
  }

  /**
   * Comparison rule for comparing two Car objects. In this comparator,
   * a car is considered as lower (better) if it is cheaper.
   *
   * @return a comparator object with price comparison logic
   */
  public static Comparator<Car> compareByPrice() {
    return Comparator.comparing(Car::getPrice);
  }

  /**
   * Comparison rule for comparing two Car objects. In this comparator,
   * a car is considered as lower (better) if it has been used less.
   *
   * @return a comparator object with mileage comparison logic
   */
  public static Comparator<Car> compareByMileage() {
    return Comparator.comparing((Car car)-> car.getMileage());
  }

  /**
   * Comparison rule for comparing two Car objects. In this comparator,
   * a car is considered as lower (better) if it is manufacture more
   * recently.
   *
   * @return a comparator object with year comparison logic
   */
  public static Comparator<Car> compareByYear() {
    return (Car car1, Car car2)-> car1.getYear() - car2.getYear();
  }

  /**
   * Comparison rule for comparing two Car objects. In this comparator, a
   * car is considered as lower (better) if it dominates the other car in
   * more aspects.
   *
   * @return a comparator object with pareto comparison logic
   */
  public static Comparator<Car> compareByPareto() {
    return (Car car1, Car car2)-> {
      int sum = 0;
      sum += (int) Math.signum(car1.getPrice() - car2.getPrice());
      sum += (int) Math.signum(car1.getMileage() - car2.getMileage());
      sum += (int) Math.signum(car1.getYear() - car2.getYear());
      return sum;
    };
  }

  /**
   * This class is to test the design and must be allowed to be instantiated.
   */
  private CarMain() {
  }
}
