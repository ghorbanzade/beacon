//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw01;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* This class implements polygon interface to construct rectangles.
*
* @author   Pejman Ghorbanzade
* @see      Polygon
* @see      Triangle
*/
public class Rectangle implements Polygon {
  ArrayList<Point2D> points = new ArrayList<Point2D>();

  /**
  * A rectangle is constructed by giving coordinates of its four vertices.
  * <b>It is assumed that four given points can make a rectangle</b>.
  *
  * @param pointA coordinates of vertex A of the rectangle
  * @param pointB coordinates of vertex B of the rectangle
  * @param pointC coordinates of vertex C of the rectangle
  * @param pointD coordinates of vertex D of the rectangle
  */
  public Rectangle(Point2D pointA,
                   Point2D pointB,
                   Point2D pointC,
                   Point2D pointD) {
    this.points.add(pointA);
    this.points.add(pointB);
    this.points.add(pointC);
    this.points.add(pointD);
  }

  /**
  * This method calculates area of the rectangle by finding the two vertices
  * that are either closest or farthest from the origin.
  *
  * @return area of the rectangle
  */
  public double getArea() {
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

  /**
  * This getter method returns an ArrayList coordinates of vertices of the
  * rectangle.
  *
  * @return coordinates of vertices of the rectangle
  */
  public ArrayList<Point2D> getPoints() {
    return points;
  }
}
