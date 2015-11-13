package edu.umb.cs680.hw01.rev2;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.geom.Point2D;

public class PolygonMain {
	public static void main(String[] args) {
		ArrayList<Polygon> polygons = new ArrayList<Polygon>();
		TreeSet<Point2D> points = new TreeSet<Point2D>(new PointComp());
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(2, 2));
		points.add(new Point2D.Double(1, 3));
		polygons.add(new Triangle(points));
		points.clear();
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 2));
		points.add(new Point2D.Double(4, 0));
		points.add(new Point2D.Double(4, 2));
		polygons.add(new Rectangle(points));
		points.clear();
		points.add(new Point2D.Double(1, 1));
		points.add(new Point2D.Double(3, 1));
		points.add(new Point2D.Double(2, 2));
		polygons.add(new Triangle(points));
		points.clear();
		points.add(new Point2D.Double(1, 1));
		points.add(new Point2D.Double(3, 1));
		points.add(new Point2D.Double(1, 6));
		points.add(new Point2D.Double(3, 6));
		polygons.add(new Rectangle(points));
		points.clear();
		for (Polygon polygon: polygons) {
			System.out.println(polygon.getPoints());
			System.out.println(polygon.getArea());
			System.out.println(polygon.getCentroid());
		}
	}
}
