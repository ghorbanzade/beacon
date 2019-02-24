//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw08;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* This class provides the method to calculate the area of the polygon in
* case it is a rectangle.
*
* @author Pejman Ghorbanzade
*/
public class RectangleAreaCalc implements AreaCalculator {
  /**
  * This method calculates area of the rectangle by finding the two vertices
  * that are either closest or farthest from the origin.
  *
  * @return area of the rectangle
  */
  public double getArea(ArrayList<Point2D> points) {
    Point2D max = new Point2D.Double(Double.MIN_VALUE, Double.MIN_VALUE);
    Point2D min = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);
    for (Point2D point: points) {
      if (point.getX() > max.getX() && point.getY() > max.getY()) {
        max.setLocation(point);
      }
      if (point.getX() < min.getX() && point.getY() < min.getY()) {
        min.setLocation(point);
      }
    }
    double width = max.getX() - min.getX();
    double height = max.getY() - min.getY();
    return height * width;
  }
}
