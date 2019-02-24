//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw03;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This functional interface is implemented by any (anonymous) class that
 * provides a logic for calculating area of a given polygon.
 *
 * @author Pejman Ghorbanzade
 * @see Polygon
 */
@FunctionalInterface
public interface AreaCalculator {
  /**
   * The method to get the area of the polygon.
   *
   * @param polygon the polygon object whose area is to be calculated
   * @return area of the polygon
   */
  public abstract double getArea(Polygon polygon);
}
