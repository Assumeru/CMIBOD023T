package hro.cmibod023t.cluster;

import java.util.ArrayList;
import java.util.Collection;

import hro.cmibod023t.cluster.balltree.BallTree;
import hro.cmibod023t.cluster.points.KDPoint;
import hro.cmibod023t.collection.CollectionWrapper;

public class KDPointCollection<E extends KDPoint> extends CollectionWrapper<E> implements PointCollection<E> {
	private final double epsilon;
	private final double epsilonSq;
	private BallTree<E> tree;

	public KDPointCollection(double epsilon) {
		super(new ArrayList<>());
		this.epsilon = epsilon;
		epsilonSq = epsilon * epsilon;
	}

	@Override
	public boolean add(E e) {
		tree = null;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		tree = null;
		return super.addAll(c);
	}

	@Override
	public boolean remove(Object o) {
		tree = null;
		return super.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		tree = null;
		return super.removeAll(c);
	}

	@Override
	public Collection<E> getNeighbours(E p) {
		if(tree == null) {
			tree = new BallTree<>(collection);
		}
		return tree.getNeighbours(p, epsilon, epsilonSq);
	}
}
