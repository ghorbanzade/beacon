//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw01;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
* This class tests the program design by using instantiating two triangles and
* two rectangles and printing their areas and showing coordinates of their
* vertices.
*
* @author   Pejman Ghorbanzade
* @see      Rectangle
* @see      Triangle
*/
public final class PolygonMain {

  /**
  * This main method tests the program design by creating 4 polygons,
  * two triangles and two rectangles.
  *
  * @param args command line arguments given to this program
  */
  public static void main(String[] args) {
    ArrayList<Polygon> polygons = new ArrayList<Polygon>();
    polygons.add(new Triangle(
        new Point2D.Double(0, 0),
        new Point2D.Double(2, 2),
        new Point2D.Double(1, 3)
    ));
    polygons.add(new Rectangle(
        new Point2D.Double(0, 0),
        new Point2D.Double(0, 2),
        new Point2D.Double(4, 0),
        new Point2D.Double(4, 2)
    ));
    polygons.add(new Triangle(
        new Point2D.Double(1, 1),
        new Point2D.Double(3, 1),
        new Point2D.Double(2, 2)
    ));
    polygons.add(new Rectangle(
        new Point2D.Double(1, 1),
        new Point2D.Double(3, 1),
        new Point2D.Double(1, 6),
        new Point2D.Double(3, 6)
    ));
    Iterator<Polygon> it = polygons.iterator();
    while (it.hasNext()) {
      Polygon nextP = it.next();
      System.out.println(nextP.getArea());
    }
  }

  /**
  * This class must not be instantiated.
  */
  private PolygonMain() {
  }
}
