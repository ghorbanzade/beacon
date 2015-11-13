package edu.umb.cs680.hw01.rev2;

import java.util.TreeSet;
import java.awt.geom.Point2D;

public class Rectangle extends Polygon {
	public Rectangle(TreeSet<Point2D> points) {
		for (Point2D point: points)
			this.points.add(point);
	}
	public double getArea() {
		Point2D max = this.points.last();
		Point2D min = this.points.first();
		double width = max.getX() - min.getX();
		double height = max.getY() - min.getY();
		double area = height * width;
		return area;
	}
}
