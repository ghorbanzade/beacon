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
