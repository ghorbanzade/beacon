//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw09;

/**
* This class defines our conception of a car to fulfill the requirements
* given in the problem. We assume a car is defined by its price, mileage
* and manufacturing year. We assume these parameters are fixed and thus
* not subject to change. Therefore, no setter method has been defined.
*
* @author   Pejman Ghorbanzade
* @see      CarComparator
*/
public final class Car {
  /**
  * We define the set of parameters for a car object, based on requirements
  * of the problem.
  */
  private final int price;
  private final int mileage;
  private final int year;

  /**
  * Based on requirements of given problem, a car is defined by its price,
  * its mileage and its manufacturing year.
  *
  * @param price    the estimated price of the car
  * @param mileage  the number of miles the car has traveled
  * @param year     the year in which the car is manufactured
  */
  public Car(int price, int mileage, int year) {
    this.price = price;
    this.mileage = mileage;
    this.year = year;
  }

  /**
  * A getter method to access the estimated price of a car.
  *
  * @return the estimated price of the car
  */
  public int getPrice() {
    return this.price;
  }

  /**
  * getter method to access the mileage of a car.
  *
  * @return the number of miles the car has traveled
  */
  public int getMileage() {
    return this.mileage;
  }

  /**
  * getter method to access the year in which the car is manufactured.
  *
  * @return the manufacturing year of the car
  */
  public int getYear() {
    return this.year;
  }

  /**
  * This method overrides toString method to enable printing the information
  * via a simple System print call.
  *
  * @return A string literal containing the information of an individual car
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Price: %6d\tMileage: %6d\tYear: %4d", price, mileage, year));
    return sb.toString();
  }
}
