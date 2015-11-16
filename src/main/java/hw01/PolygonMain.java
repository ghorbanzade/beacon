package edu.umb.cs680.hw01;

import java.awt.Point;
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
public class PolygonMain {

  /**
  * This main method tests the program design by creating 4 polygons,
  * two triangles and two rectangles.
  */
  public static void main(String[] args) {
    ArrayList<Polygon> polygons = new ArrayList<Polygon>();
    polygons.add(new Triangle(
        new Point(0, 0),
        new Point(2, 2),
        new Point(1, 3)
    ));
    polygons.add(new Rectangle(
        new Point(0, 0),
        new Point(0, 2),
        new Point(4, 0),
        new Point(4, 2)
    ));
    polygons.add(new Triangle(
        new Point(1, 1),
        new Point(3, 1),
        new Point(2, 2)
    ));
    polygons.add(new Rectangle(
        new Point(1, 1),
        new Point(3, 1),
        new Point(1, 6),
        new Point(3, 6)
    ));
    Iterator<Polygon> it = polygons.iterator();
    Polygon nextP = it.next();
    while (it.hasNext()) {
      System.out.println(nextP.getPoints());
      System.out.println(nextP.getArea());
    }
  }
}
