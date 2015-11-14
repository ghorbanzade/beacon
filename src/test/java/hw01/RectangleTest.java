package edu.umb.cs680.hw01;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.awt.Point;

public class RectangleTest {
	@Test
	public void testGetArea(){
		Rectangle rectangle = new Rectangle(
			new Point(-1, -1),
			new Point(-1, 1),
			new Point(1, -1),
			new Point(1, 1)
		);
		assertThat(rectangle.getArea(), is(4.0));
	}
}
