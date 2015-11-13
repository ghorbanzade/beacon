package edu.umb.cs680.hw01.rev2;

import java.util.TreeSet;
import java.util.Comparator;
import java.awt.geom.Point2D;

public class PointComp implements Comparator<Point2D>{
	@Override
	public int compare(Point2D point1, Point2D point2) {
		double dist1 = Math.sqrt(Math.pow(point1.getX(), 2) + Math.pow(point1.getY(), 2));
		double dist2 = Math.sqrt(Math.pow(point2.getX(), 2) + Math.pow(point2.getY(), 2));
		return (dist1 > dist2) ? 1 : -1;
	}
}
