//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw03;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class demonstrate the design by creating a triangle and transforming
 * it to a rectangle and back to triangle again and printing the area of the
 * polygon at each stage.
 *
 * @author Pejman Ghorbanzade
 * @see AreaCalculator
 * @see Polygon
 */
public final class PolygonMain {
  /**
   * The main method of this program creates a triangle and prints its area.
   * It then transforms the triangle into a rectangle and prints the area
   * again. Finally, the method demonstrates that it is possible to dynamically
   * transfer the rectangle to a triangle by removing one of its points.
   *
   * @param args command line arguments given to the program
   */
  public static void main(String[] args) {
    try {
      ArrayList<Point2D> points = new ArrayList<Point2D>();
      points.add(new Point2D.Double(0, 0));
      points.add(new Point2D.Double(4, 0));
      points.add(new Point2D.Double(0, 3));
      Polygon polygon = new Polygon(points);
      System.out.println(polygon.getArea());
      polygon.addPoint(new Point2D.Double(4, 3));
      System.out.println(polygon.getArea());
      polygon.removePoint(new Point2D.Double(4, 0));
      System.out.println(polygon.getArea());
    } catch (PolygonException e) {
      e.printStackTrace();
    }
  }

  /**
   * This class must not be instantiated.
   */
  private PolygonMain() {
  }
}
