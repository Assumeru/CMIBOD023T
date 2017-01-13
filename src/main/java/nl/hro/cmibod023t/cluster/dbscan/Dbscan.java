package nl.hro.cmibod023t.cluster.dbscan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.hro.cmibod023t.cluster.Cluster;
import nl.hro.cmibod023t.cluster.DefaultPointCollection;
import nl.hro.cmibod023t.cluster.PointCollection;
import nl.hro.cmibod023t.cluster.points.Point;

public class Dbscan<E extends Point> {
	private final int minPoints;
	private final PointCollection<E> points;

	public Dbscan(double epsilon, int minPoints) {
		this(minPoints, new DefaultPointCollection<>(epsilon));
	}

	public Dbscan(int minPoints, PointCollection<E> points) {
		this.minPoints = minPoints;
		this.points = points;
	}

	@SuppressWarnings("unchecked")
	public void addPoints(E... points) {
		addPoints(Arrays.asList(points));
	}

	public void addPoints(Collection<E> points) {
		this.points.addAll(points);
	}

	public Collection<Cluster<E>> cluster() {
		List<Cluster<E>> clusters = new ArrayList<>();
		Set<E> visited = new HashSet<>();
		for(E p : points) {
			if(visited.contains(p)) {
				continue;
			}
			visited.add(p);
			Collection<E> neighbours = points.getNeighbours(p);
			if(neighbours.size() >= minPoints) {
				clusters.add(createCluster(neighbours, visited));
			}
		}
		return clusters;
	}

	private Cluster<E> createCluster(Collection<E> neighbours, Set<E> visited) {
		Cluster<E> c = new Cluster<>();
		List<E> points = new ArrayList<>(neighbours);
		for(int i = 0; i < points.size(); i++) {
			E p = points.get(i);
			if(!visited.contains(p)) {
				visited.add(p);
				Collection<E> n = this.points.getNeighbours(p);
				if(n.size() >= minPoints) {
					points.addAll(n);
				}
			}
			if(p.getCluster() == null) {
				c.add(p);
			}
		}
		return c;
	}
}
