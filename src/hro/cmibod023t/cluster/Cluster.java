package hro.cmibod023t.cluster;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import hro.cmibod023t.cluster.points.Point;

public class Cluster extends AbstractCollection<Point> {
	private final Collection<Point> points;
	private final Collection<Point> immutable;

	public Cluster() {
		points = new ArrayList<>();
		immutable = Collections.unmodifiableCollection(points);
	}

	@Override
	public boolean add(Point e) {
		e.setCluster(this);
		return points.add(e);
	}

	@Override
	public Iterator<Point> iterator() {
		return immutable.iterator();
	}

	@Override
	public int size() {
		return points.size();
	}
}
