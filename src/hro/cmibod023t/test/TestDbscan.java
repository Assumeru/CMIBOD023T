package hro.cmibod023t.test;

import java.util.Collection;

import hro.cmibod023t.cluster.Cluster;
import hro.cmibod023t.cluster.dbscan.Dbscan;
import hro.cmibod023t.cluster.points.EuclidianDoublePoint;

public class TestDbscan {
	public static void main(String[] args) {
		Dbscan<P> dbscan = new Dbscan<>(10, 2);
		dbscan.addPoints(new P(0, 0), new P(5, 1), new P(3, 2), new P(10, 0), new P(12, 3), new P(30, 15), new P(25, 20));
		Collection<Cluster> clusters = dbscan.cluster();
		System.out.println(clusters);
	}

	private static class P extends EuclidianDoublePoint {
		public P(double x, double y) {
			super(x, y);
		}

		public double getX() {
			return dimensions[0];
		}

		public double getY() {
			return dimensions[1];
		}

		@Override
		public String toString() {
			return "<" + getX() + ", " + getY() + ">";
		}
	}
}
