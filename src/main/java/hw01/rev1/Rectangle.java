package edu.umb.cs680.hw01.rev1;

import java.util.ArrayList;
import java.awt.Point;

public class Rectangle implements Polygon {
	ArrayList<Point> points = new ArrayList<Point>(4);
	public Rectangle(Point pointA, Point pointB, Point pointC, Point pointD) {
		this.points.add(pointA);
		this.points.add(pointB);
		this.points.add(pointC);
		this.points.add(pointD);
	}
	public double getArea() {
		Point max = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		for (Point point: points) {
			if (point.getX() > max.getX() && point.getY() > max.getY())
				max.setLocation(point);
			if (point.getX() < min.getX() && point.getY() < min.getY())
				min.setLocation(point);
		}
		double width = max.getX() - min.getX();
		double height = max.getY() - min.getY();
		double area = height * width;
		return area;
	}
	public ArrayList<Point> getPoints() {
		return points;
	}
}
