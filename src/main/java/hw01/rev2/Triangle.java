package edu.umb.cs680.hw01.rev2;

import java.util.TreeSet;
import java.util.Iterator;
import java.awt.geom.Point2D;

public class Triangle extends Polygon {
	public Triangle(TreeSet<Point2D> points) {
		for (Point2D point: points)
			this.points.add(point);
	}
	public double getArea() {
		double[] sides = new double[3];
		int i = 0;
		Iterator<Point2D> it = this.points.iterator();
		Point2D currentPoint = it.next();
		while (it.hasNext()) {
			Point2D nextPoint = it.next();
			sides[i] = currentPoint.distance(nextPoint);
			currentPoint = nextPoint;
			i++;
		}
		sides[i] = this.points.last().distance(this.points.first());
		double heron = (sides[0] + sides[1] + sides[2]) / 2;
		double area = Math.sqrt(heron * (heron - sides[0]) * (heron - sides[1]) * (heron - sides[2]));
		return area;
	}
}
