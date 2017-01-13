package nl.hro.cmibod023t.cluster;

import java.util.Collection;

import nl.hro.cmibod023t.cluster.points.Point;

public interface PointCollection<E extends Point> extends Collection<E> {
	Collection<E> getNeighbours(E p);
}
