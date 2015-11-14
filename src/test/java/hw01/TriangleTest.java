package edu.umb.cs680.hw01;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
// import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.awt.Point;

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
