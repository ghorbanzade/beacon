package edu.umb.cs680.hw01.rev2;

import java.util.TreeSet;
import java.awt.geom.Point2D;

public abstract class Polygon {
	TreeSet<Point2D> points = new TreeSet<Point2D>(new PointComp());
	public abstract double getArea();
	public Point2D.Double getCentroid() {
		double sumX = 0;
		double sumY = 0;
		for (Point2D point: this.points ) {
			sumX += point.getX();
			sumY += point.getY();
		}
		double centroidX = sumX / this.points.size();
		double centroidY = sumY / this.points.size();
		return new Point2D.Double(centroidX, centroidY);
	}
	public TreeSet<Point2D> getPoints() {
		return this.points;
	}
}
