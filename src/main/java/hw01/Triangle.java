package edu.umb.cs680.hw01;

import java.util.ArrayList;
import java.awt.Point;

public class Triangle implements Polygon {
	ArrayList<Point> points = new ArrayList<Point>(3);
	public Triangle(Point pointA, Point pointB, Point pointC) {
		this.points.add(pointA);
		this.points.add(pointB);
		this.points.add(pointC);
	}
	public double getArea() {
		double[] sides = {
			this.points.get(0).distance(this.points.get(1)),
			this.points.get(1).distance(this.points.get(2)),
			this.points.get(2).distance(this.points.get(0))
		};
		double heron = (sides[0] + sides[1] + sides[2]) / 2;
		return Math.sqrt(heron * (heron - sides[0]) * (heron - sides[1]) * (heron - sides[2]));
	}
	public ArrayList<Point> getPoints() {
		return points;
	}
}
