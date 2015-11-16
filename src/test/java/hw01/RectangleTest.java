package edu.umb.cs680.hw01;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.awt.Point;
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
}
