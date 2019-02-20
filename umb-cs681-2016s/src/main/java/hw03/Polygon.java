//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw03;

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
   * A polygon can be constructed using a given non-empty list of points
   * (vertices) and a method for calculating its area should be specified.
   *
   * <p>Design Problem: This constructor allows any set of points to be
   * treated as a polygon.
   *
   * @param points the list of points (vertices) of the polygon object
   * @param areaCalc the logic to compute the area of the the polygon object
   */
  public Polygon(ArrayList<Point2D> points, AreaCalculator areaCalc) {
    this.points = Objects.requireNonNull(points);
    this.areaCalc = areaCalc;
  }

  /**
   * A polygon can be constructed using a given non-empty list of points.
   * This design tries to infer the logic for computing the area of the
   * polygon.
   *
   * @param points the list of points (vertices) of the polygon object
   */
  public Polygon(ArrayList<Point2D> points) {
    this(points, null);
    this.interpretAreaCalc();
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
    this.interpretAreaCalc();
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
      throw new PolygonException("point not found.");
    }
    this.interpretAreaCalc();
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
   * This getter method returns the list of vertices defining the polygon.
   *
   * @return the list of coordinates of vertices of the polygon
   */
  public ArrayList<Point2D> getPoints() {
    return this.points;
  }

  /**
   * This method will find the area of the polygon based on the logic specified
   * by client.
   *
   * <p>Design Problem: It is client's responsibility to properly implement the
   * areaCalculator interface.
   *
   * @return the area of the polygon
   * @throws PolygonException if no method is specified for computing area
   */
  public double getArea() throws PolygonException {
    if (Objects.isNull(this.areaCalc)) {
      throw new PolygonException("area calculator not specified");
    }
    return this.areaCalc.getArea(this);
  }

  /**
   * this helper method infers the method with which to compute the area of
   * the polygon.
   */
  private void interpretAreaCalc() {
    switch (this.points.size()) {
      case 3:
        this.areaCalc = calcTriangle();
        break;
      case 4:
        this.areaCalc = calcRectangle();
        break;
      default: break;
    }
  }

  /**
   * This method provides the logic for computing area of a triangle
   * as a functional expression.
   *
   * @return an implementation of the area calculator for triangles
   */
  private AreaCalculator calcTriangle() {
    return (Polygon polygon)-> {
      double[] sides = {
        this.points.get(0).distance(this.points.get(1)),
        this.points.get(1).distance(this.points.get(2)),
        this.points.get(2).distance(this.points.get(0))
      };
      double heron = (sides[0] + sides[1] + sides[2]) / 2;
      return Math.sqrt(
          heron * (heron - sides[0]) * (heron - sides[1]) * (heron - sides[2])
      );
    };
  }

  /**
   * This method provides the logic for computing area of a rectangle
   * as a functional expression.
   *
   * @return an implementation of the area calculator for rectangles
   */
  private AreaCalculator calcRectangle() {
    return (Polygon polygon)-> {
      Point2D max = new Point2D.Double(Double.MIN_VALUE, Double.MIN_VALUE);
      Point2D min = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);
      for (Point2D point: this.points) {
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
}
