package edu.umb.cs680.hw01;

import java.awt.Point;
import java.util.ArrayList;

/**
* This class implements polygon interface to construct triangles.
*
* @author   Pejman Ghorbanzade
* @see      Polygon
* @see      Rectangle
*/
public class Triangle implements Polygon {
  ArrayList<Point> points = new ArrayList<Point>();

  /**
  * A triangle is constructed by giving coordinates of its three vertices.
  * <b>It is assumed that three given points can make a triangle</b>.
  *
  * @param pointA coordinates of vertex A of the triangle
  * @param pointB coordinates of vertex B of the triangle
  * @param pointC coordinates of vertex C of the triangle
  */
  public Triangle(Point pointA, Point pointB, Point pointC) {
    this.points.add(pointA);
    this.points.add(pointB);
    this.points.add(pointC);
  }

  /**
  * This method calculates area of the triangle by finding its sides and using
  * them in Heron's formula.
  *
  * @return area of the triangle
  */
  public double getArea() {
    double[] sides = {
      this.points.get(0).distance(this.points.get(1)),
      this.points.get(1).distance(this.points.get(2)),
      this.points.get(2).distance(this.points.get(0))
    };
    double heron = (sides[0] + sides[1] + sides[2]) / 2;
    return Math.sqrt(heron * (heron - sides[0]) * (heron - sides[1]) * (heron - sides[2]));
  }

  /**
  * This getter method returns an ArrayList coordinates of vertices of the
  * triangle.
  *
  * @return coordinates of vertices of the triangle
  */
  public ArrayList<Point> getPoints() {
    return points;
  }
}
