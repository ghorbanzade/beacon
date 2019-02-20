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
* This class provides the method to calculate the area of the polygon in case
* it is a triangle.
*
* @author Pejman Ghorbanzade
*/
public class TriangleAreaCalc implements AreaCalculator {
  /**
  * This method calculates area of the triangle by finding its sides and using
  * them in Heron's formula.
  *
  * @return area of the triangle
  */
  public double getArea(ArrayList<Point2D> points) {
    double[] sides = {
      points.get(0).distance(points.get(1)),
      points.get(1).distance(points.get(2)),
      points.get(2).distance(points.get(0))
    };
    double heron = (sides[0] + sides[1] + sides[2]) / 2;
    return Math.sqrt(heron * (heron - sides[0]) * (heron - sides[1]) * (heron - sides[2]));
  }
}
