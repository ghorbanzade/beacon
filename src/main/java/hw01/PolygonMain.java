package edu.umb.cs680.hw01;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;

public class PolygonMain {
	public static void main(String[] args) {
		ArrayList<Polygon> p = new ArrayList<Polygon>();
		p.add(new Triangle(
			new Point(0, 0),
			new Point(2, 2),
			new Point(1, 3)
		));
		p.add(new Rectangle(
			new Point(0, 0),
			new Point(0, 2),
			new Point(4, 0),
			new Point(4, 2)
		));
		p.add(new Triangle(
			new Point(1, 1),
			new Point(3, 1),
			new Point(2, 2)
		));
		p.add(new Rectangle(
			new Point(1, 1),
			new Point(3, 1),
			new Point(1, 6),
			new Point(3, 6)
		));
		Iterator<Polygon> it = p.iterator();
		while (it.hasNext()) {
			Polygon nextP = it.next();
			System.out.println(nextP.getPoints());
			System.out.println(nextP.getArea());
		}
	}
}
