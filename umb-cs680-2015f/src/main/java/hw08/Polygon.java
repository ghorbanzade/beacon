//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw08;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* This class defines a polygon as a set of points. This class allows client
* to dynamically modify the shape of the polygon and get the area of the
* polygon using a set of predeveloped algorithms for specific shapes.
*
* @author Pejman Ghorbanzade
* @see AreaCalculator
*/
public class Polygon {
  /**
  * Each polygon object will have a list of coordinates of its vertices
  * as well as the algorithm with which we can calculate its area.
  */
  private final ArrayList<Point2D> points;
  private AreaCalculator areaCalc;

  /**
  * This constructor allows any set of points to be treated as a polygon.
  * Although this can be considered as a design problem, the implementation
  * follows the instructions given in problem statement.
  *
  * @param points the list of coordinates of the vertices constructing
  *               the polygon
  */
  public Polygon(ArrayList<Point2D> points) {
    this.points = points;
  }

  /**
  * This method allows user to add a new point to the set of points defining
  * the polygon and dynamically modify the shape of the polygon.
  *
  * <p>Based on current desing, every time a new point is added to the polygon
  * the client must make calls to setAreaCalc method to set the algorithm that
  * correctly returns the area of the new polygon.
  *
  * @param point the new point of the polygon
  */
  public void addPoint(Point2D point) {
    this.points.add(point);
  }

  /**
  * This method allows client to remove one of the points that construct the
  * polygon and therefore dynamically modify the shape of the polygon.
  *
  * <p>Based on current design, the client is responsible ot set the right
  * algorithm that finds area of the new polygon.
  *
  * @param point the point to be removed from the polygon
  * @throws PolygonException in case the specified point is not amongst the
  *         set of points defining the polygon
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
  * @param areaCalc an instance of one of the algorithms that implement
  *                 AreaCalculator interface.
  */
  public void setAreaCalc(AreaCalculator areaCalc) {
    this.areaCalc = areaCalc;
  }

  /**
  * This method will provide access to the set of points defining the shape
  * of the polygon.
  *
  * @return the list of coordinates of vertices of the polygon
  */
  public ArrayList<Point2D> getPoints() {
    return this.points;
  }

  /**
  * This method will find the area of the polygon by invoking the algorithm
  * set by client for that purpose. It is client's responsibility to have set
  * the proper areaCalculator when caling this method.
  *
  * @return the area of the polygon
  */
  public double getArea() {
    return this.areaCalc.getArea(this.points);
  }

  /**
  * Regardless of its shape, the centroid of a polygon is computed simply
  * by averaging coordinates of its vertices. This is the reason why it is
  * not justifiable to create a CentroidCalculator interface and having
  * different calculators to implement this method separately in different
  * classes.
  *
  * @return coordinates of the centroid of the polygon
  */
  public Point2D getCentroid() {
    double sumX = 0;
    double sumY = 0;
    for (Point2D point: this.points ) {
      sumX += point.getX();
      sumY += point.getY();
    }
    double centroidX = sumX / this.points.size();
    double centroidY = sumY / this.points.size();
    return new Point2D.Double(centroidX, centroidY);
  }
}
