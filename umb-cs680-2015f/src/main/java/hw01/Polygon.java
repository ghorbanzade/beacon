//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw01;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* This interface defines two methods to be implemented by Triangle and
* Rectangle classes.
*
* @author   Pejman Ghorbanzade
* @see      Rectangle
* @see      Triangle
*/
public interface Polygon {
  /**
  * This method would simply return a list of coordinates of vertices defining
  * the polygon.
  *
  * @return a list of coordinates of vertices of the polygon
  */
  public ArrayList<Point2D> getPoints();

  /**
  * This method would return the area of the polygon.
  *
  * @return area of the Polygon object
  */
  public double getArea();
}
