package nl.hro.cmibod023t.exercises;

import nl.hro.cmibod023t.cluster.points.EuclidianDoublePoint;

public class Star extends EuclidianDoublePoint {
	private final int id;

	public Star(double x, double y, double z, int id) {
		super(x, y, z);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public double getX() {
		return dimensions[0];
	}

	public double getY() {
		return dimensions[1];
	}

	public double getZ() {
		return dimensions[2];
	}

	@Override
	public String toString() {
		return "<Star " + id + " (" + getX() + ", " + getY() + ", " + getZ() + ")>";
	}
}
