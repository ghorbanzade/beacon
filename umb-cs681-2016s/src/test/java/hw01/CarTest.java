//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw01;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Arrays;

/**
* Unit test suit for Car Comparator question.
*
* @author Pejman Ghorbanzade
*/
public class CarTest {
  @Test
  public void checkCarConstructor() {
    Car car = new Car(10, 20, 30);
    assertThat(car.getPrice(), is(10));
    assertThat(car.getMileage(), is(20));
    assertThat(car.getYear(), is(30));
  }
}
