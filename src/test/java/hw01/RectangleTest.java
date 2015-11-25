package edu.umb.cs680.hw01;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.awt.Point;
import java.util.Arrays;
import java.util.ArrayList;

/**
* This class unit tests getArea() and getPoints() methods for Rectangle class.
*
* @author   Pejman Ghorbanzade
* @see      Rectangle
*/
public class RectangleTest {
  @Test
  public void testGetArea() {
    Rectangle rectangle = new Rectangle(
        new Point(-1, -1),
        new Point(-1, 1),
        new Point(1, -1),
        new Point(1, 1)
    );
    assertThat(rectangle.getArea(), is(4.0));
  }

  @Test
  public void testGetPoints() {
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
    assertThat(rectangle.getPoints(), is(array));
  }
}
