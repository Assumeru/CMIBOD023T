package hro.cmibod023t.cluster.points;

import hro.cmibod023t.cluster.Cluster;

public abstract class AbstractPoint<E extends Point> implements Point {
	private Cluster cluster;

	@SuppressWarnings("unchecked")
	@Override
	public double getDistance(Point p) {
		try {
			return getDistanceInternal((E) p);
		} catch(ClassCastException e) {
			throw new IllegalArgumentException(this + " cannot be compared to " + p, e);
		}
	}

	protected abstract double getDistanceInternal(E p);

	@Override
	public void setCluster(Cluster c) {
		cluster = c;
	}

	@Override
	public Cluster getCluster() {
		return cluster;
	}
}
