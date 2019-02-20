//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw08;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* This interface defines the api method to calculate area of polygons. Each
* type of polygon can implement the algorithm to find the method.
*
* @author Pejman Ghorbanzade
* @see TriangleAreaCalc
* @see RectangleAreaCalc
*/
public interface AreaCalculator {
  /**
  * The method to get the area of the polygon.
  *
  * @param points the list of coordinates of vertices of the polygon
  * @return area of the polygon
  */
  public double getArea(ArrayList<Point2D> points);
}
