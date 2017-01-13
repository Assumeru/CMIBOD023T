package nl.hro.cmibod023t.cluster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.hro.cmibod023t.cluster.points.Point;
import nl.hro.cmibod023t.collection.CollectionWrapper;

public class DefaultPointCollection<E extends Point> extends CollectionWrapper<E> implements PointCollection<E> {
	private final double epsilon;

	public DefaultPointCollection(double epsilon) {
		super(new ArrayList<>());
		this.epsilon = epsilon * epsilon;
	}

	@Override
	public Collection<E> getNeighbours(E p) {
		List<E> neighbours = new ArrayList<>();
		for(E neighbour : collection) {
			if(p.getDistance(neighbour) <= epsilon) {
				neighbours.add(neighbour);
			}
		}
		return neighbours;
	}
}
