//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw02;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class defines a polygon as a set of points. This class allows client
 * to dynamically modify the shape of the polygon and get its area.
 *
 * @author Pejman Ghorbanzade
 * @see AreaCalculator
 */
public class Polygon {

  /**
   * Each polygon object has a list of coordinates of its vertices
   * as well as the algorithm with which we can calculate its area.
   */
  private final ArrayList<Point2D> points;
  private AreaCalculator areaCalc;

  /**
   * To construct a polygon, a non-empty list of points (vertices) should be
   * given and a method for calculating its area should be specified.
   *
   * <p>Design Problem: This constructor allows any set of points to be
   * treated as a polygon.
   *
   * @param points the list of points (vertices) of the polygon object
   * @param areaCalc the logic to compute the area of the the polygon object
   */
  public Polygon(ArrayList<Point2D> points, AreaCalculator areaCalc) {
    this.points = Objects.requireNonNull(points);
    this.areaCalc = Objects.requireNonNull(areaCalc);
  }

  /**
   * This method allows user to add a new point to the set of points defining
   * the polygon and hence to modify the shape of the polygon.
   *
   * <p>Design Problem: every time a vertex is added to the polygon,
   * the client must make a call to setAreaCalc method to update the
   * logic that describes the area of the polygon.
   *
   * @param point a new point (vertex) for the polygon
   */
  public void addPoint(Point2D point) {
    this.points.add(point);
  }

  /**
   * This method allows client to remove one of the vertices of the
   * polygon and hence to modify the shape of the polygon.
   *
   * <p>Design Problem: every time a vertex is removed from the polygon,
   * the client must make a call to setAreaCalc method to update the
   * logic that describes the area of the polygon.
   *
   * @param point the point to be removed from the polygon
   * @throws PolygonException in case the specified point is not amongst the
   *         set of vertices defining the polygon
   */
  public void removePoint(Point2D point) throws PolygonException {
    if (!this.points.remove(point)) {
      throw new PolygonException("Point not found.");
    }
  }

  /**
   * This method allows client to set the algorithm with which the area of
   * the polygon should be calculated. If set incorrectly, any call to getArea
   * method will result in a wrong response.
   *
   * <p>This is considered a design smell but since the problem explicitly
   * asks to let user set the algorithm, the assumption has been made that
   * user will set the right areaCalculator every time the set of coordinates
   * are modified.
   *
   * @param areaCalc an implementation of the AreaCalculator interface
   */
  public void setAreaCalc(AreaCalculator areaCalc) {
    this.areaCalc = Objects.requireNonNull(areaCalc);
  }

  /**
   * This method will find the area of the polygon based on the logic specified
   * by client.
   *
   * <p>Design Problem: It is client's responsibility to properly implement the
   * areaCalculator interface.
   *
   * @return the area of the polygon
   */
  public double getArea() {
    return this.areaCalc.getArea(this.points);
  }
}
