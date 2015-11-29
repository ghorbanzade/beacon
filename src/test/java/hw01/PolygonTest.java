package edu.umb.cs680.hw01;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/**
* This class unit tests getArea() and getPoints() methods for Rectangle class.
*
* @author   Pejman Ghorbanzade
* @see      Polygon
*/
public class PolygonTest {
  @Test
  public void testTriangleArea() {
    Triangle triangle = new Triangle(
        new Point(-1, -1),
        new Point(-1, 1),
        new Point(1, -1)
    );
    assertThat(triangle.getArea(), is(closeTo(2.0, 0.01)));
  }

  @Test
  public void testRectangleArea() {
    Rectangle rectangle = new Rectangle(
        new Point(-1, -1),
        new Point(-1, 1),
        new Point(1, -1),
        new Point(1, 1)
    );
    assertThat(rectangle.getArea(), is(4.0));
  }

  @Test
  public void testTrianglePoints() {
    ArrayList<Point> array = new ArrayList<Point>();
    array.add(new Point(-1, -1));
    array.add(new Point(-1, 1));
    array.add(new Point(1, -1));
    array.add(new Point(1, 1));
    Rectangle rectangle = new Rectangle(
        array.get(0),
        array.get(1),
        array.get(2),
        array.get(3)
    );
    assertThat(rectangle.getPoints().size(), is(4));
    assertThat(rectangle.getPoints(), is(array));
  }

  @Test
  public void testRectanglePoints() {
    ArrayList<Point> array = new ArrayList<Point>();
    array.add(new Point(-1, -1));
    array.add(new Point(-1, 1));
    array.add(new Point(1, -1));
    Triangle triangle = new Triangle(
        array.get(0),
        array.get(1),
        array.get(2)
    );
    assertThat(triangle.getPoints().size(), is(3));
    assertThat(triangle.getPoints(), is(array));
  }

}
