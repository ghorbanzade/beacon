//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw02;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* This functional interface is implemented by any (anonymous) class
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
  * @param points the list of coordinates of vertices of the polygon
  * @return area of the polygon
  */
  public abstract double getArea(ArrayList<Point2D> points);
}
