//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw01;

import java.awt.Point;
import java.util.ArrayList;

/**
* This interface defines two methods to be implemented by Triangle and
* Rectangle classes.
*
* @author   Pejman Ghorbanzade
*/
public interface Polygon {
  public ArrayList<Point> getPoints();

  public double getArea();
}
