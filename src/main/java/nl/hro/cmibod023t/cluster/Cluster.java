package nl.hro.cmibod023t.cluster;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import nl.hro.cmibod023t.cluster.points.Point;

public class Cluster<E extends Point> extends AbstractCollection<E> {
	private final Collection<E> points;
	private final Collection<E> immutable;

	public Cluster() {
		points = new ArrayList<>();
		immutable = Collections.unmodifiableCollection(points);
	}

	@Override
	public boolean add(E e) {
		e.setCluster(this);
		return points.add(e);
	}

	@Override
	public Iterator<E> iterator() {
		return immutable.iterator();
	}

	@Override
	public int size() {
		return points.size();
	}
}
