//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw02;

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
      Polygon polygon = new Polygon(points, calcTriangle());
      System.out.println(polygon.getArea());
      polygon.addPoint(new Point2D.Double(4, 3));
      polygon.setAreaCalc(calcRectangle());
      System.out.println(polygon.getArea());
      polygon.removePoint(new Point2D.Double(4, 0));
      polygon.setAreaCalc(calcTriangle());
      System.out.println(polygon.getArea());
    } catch (PolygonException e) {
      e.printStackTrace();
    }
  }

  /**
   * This static method contains logic for computing area of a triangle
   * as a functional expression.
   *
   * @return an implementation of the area calculator for triangles
   */
  private static AreaCalculator calcTriangle() {
    return (ArrayList<Point2D> pts)-> {
      double[] sides = {
        pts.get(0).distance(pts.get(1)),
        pts.get(1).distance(pts.get(2)),
        pts.get(2).distance(pts.get(0))
      };
      double heron = (sides[0] + sides[1] + sides[2]) / 2;
      return Math.sqrt(
          heron * (heron - sides[0]) * (heron - sides[1]) * (heron - sides[2])
      );
    };
  }

  /**
   * This static method contains logic for computing area of a rectangle
   * as a functional expression.
   *
   * @return an implementation of the area calculator for rectangles
   */
  private static AreaCalculator calcRectangle() {
    return (ArrayList<Point2D> pts)-> {
      Point2D max = new Point2D.Double(Double.MIN_VALUE, Double.MIN_VALUE);
      Point2D min = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);
      for (Point2D point: pts) {
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
    };
  }

  /**
   * This class must not be instantiated.
   */
  private PolygonMain() {
  }
}
