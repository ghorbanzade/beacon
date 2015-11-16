package edu.umb.cs680.hw01;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;

/**
* This class unit tests getArea() and getPoints() methods for Triangle class.
*
* @author   Pejman Ghorbanzade
* @see      Triangle
*/
public class TriangleTest {
  @Test
  public void testGetArea() {
    Triangle triangle = new Triangle(
        new Point(-1, -1),
        new Point(-1, 1),
        new Point(1, -1)
    );
    assertThat(triangle.getArea(), is(closeTo(2.0, 0.01)));
  }
}
