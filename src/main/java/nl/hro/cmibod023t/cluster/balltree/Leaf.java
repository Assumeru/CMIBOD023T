package nl.hro.cmibod023t.cluster.balltree;

import nl.hro.cmibod023t.cluster.points.KDPoint;

class Leaf<E extends KDPoint> implements Node<E> {
	private final E value;

	public Leaf(E value) {
		this.value = value;
	}

	@Override
	public Node<E> getRight() {
		return null;
	}

	@Override
	public Node<E> getLeft() {
		return null;
	}

	@Override
	public E getValue() {
		return value;
	}
}
