//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw08;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* This method demonstrate how to use the design by creating a triangle and
* transforming it to rectangle and back to triangle again and printing the
* area of the polygon at each stage.
*
* @author   Pejman Ghorbanzade
* @see      Polygon
*/
public final class PolygonMain {
  /**
  * The main method of this program creates a triangle and prints its area
  * and its centroid. It then transforms the triangle into a rectangle
  * and prints the area and centroid again. Finally, the method will
  * demonstrate that it is possibel to dynamically transfer the rectangle
  * to triangle by removing one of the points.
  *
  * @param args the command line arguments given to the program
  */
  public static void main(String[] args) {
    try {
      ArrayList<Point2D> points = new ArrayList<Point2D>();
      points.add(new Point2D.Double(0, 0));
      points.add(new Point2D.Double(4, 0));
      points.add(new Point2D.Double(0, 3));
      Polygon polygon = new Polygon(points);
      polygon.setAreaCalc(new TriangleAreaCalc());
      System.out.println(polygon.getArea());
      System.out.println(polygon.getCentroid());
      polygon.addPoint(new Point2D.Double(4, 3));
      polygon.setAreaCalc(new RectangleAreaCalc());
      System.out.println(polygon.getArea());
      System.out.println(polygon.getCentroid());
      polygon.removePoint(new Point2D.Double(4, 0));
      polygon.setAreaCalc(new TriangleAreaCalc());
      System.out.println(polygon.getArea());
      System.out.println(polygon.getCentroid());
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
