//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw08;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
* Unit test suit for polygon question.
*
* @author Pejman Ghorbanzade
*/
public class PolygonTest {
  @Test
  public void triangleConstructor() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1, 1));
    points.add(new Point2D.Double(2, 1));
    points.add(new Point2D.Double(1, 2));
    Polygon polygon = new Polygon(points);
    assertThat(polygon.getPoints(), is(points));
  }

  @Test
  public void rectangleConstructor() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1, 1));
    points.add(new Point2D.Double(2, 1));
    points.add(new Point2D.Double(1, 2));
    points.add(new Point2D.Double(2, 2));
    Polygon polygon = new Polygon(points);
    assertThat(polygon.getPoints(), is(points));
  }

  @Test
  public void triangleArea() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1, 1));
    points.add(new Point2D.Double(2, 1));
    points.add(new Point2D.Double(1, 2));
    Polygon polygon = new Polygon(points);
    polygon.setAreaCalc(new TriangleAreaCalc());
    assertThat(polygon.getArea(), is(closeTo(0.5, 1e-6)));
  }

  @Test
  public void rectangleArea() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1, 1));
    points.add(new Point2D.Double(2, 1));
    points.add(new Point2D.Double(1, 2));
    points.add(new Point2D.Double(2, 2));
    Polygon polygon = new Polygon(points);
    polygon.setAreaCalc(new RectangleAreaCalc());
    assertThat(polygon.getArea(), is(1D));
  }

  @Test
  public void triangleCentroid() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1, 1));
    points.add(new Point2D.Double(2, 1));
    points.add(new Point2D.Double(1, 2));
    Polygon polygon = new Polygon(points);
    assertThat(polygon.getCentroid().getX(), is(closeTo(1.333, 1e-3)));
    assertThat(polygon.getCentroid().getY(), is(closeTo(1.333, 1e-3)));
  }

  @Test
  public void rectangleCentroid() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1, 1));
    points.add(new Point2D.Double(2, 1));
    points.add(new Point2D.Double(1, 2));
    points.add(new Point2D.Double(2, 2));
    Polygon polygon = new Polygon(points);
    assertThat(polygon.getCentroid().getX(), is(1.5));
    assertThat(polygon.getCentroid().getY(), is(1.5));
  }

  @Test
  public void dynamicTransform() {
    try {
      ArrayList<Point2D> points = new ArrayList<Point2D>();
      points.add(new Point2D.Double(1, 1));
      points.add(new Point2D.Double(2, 1));
      points.add(new Point2D.Double(1, 2));
      Polygon polygon = new Polygon(points);
      assertThat(polygon.getPoints().size(), is(3));
      polygon.addPoint(new Point2D.Double(2, 2));
      assertThat(polygon.getPoints().size(), is(4));
      polygon.removePoint(new Point2D.Double(1, 1));
      assertThat(polygon.getPoints().size(), is(3));
    } catch (PolygonException e) {
      fail("operation should not throw exception.");
    }
  }

  @Test
  public void removeNonExistantPoint() {
    try {
      ArrayList<Point2D> points = new ArrayList<Point2D>();
      points.add(new Point2D.Double(1, 1));
      points.add(new Point2D.Double(2, 1));
      points.add(new Point2D.Double(1, 2));
      Polygon polygon = new Polygon(points);
      polygon.removePoint(new Point2D.Double(3, 3));
      fail("exception should have been thrown");
    } catch (Exception e) {
      assertThat(e, instanceOf(PolygonException.class));
      assertThat(e.getMessage(), is("Point not found."));
    }
  }

}
