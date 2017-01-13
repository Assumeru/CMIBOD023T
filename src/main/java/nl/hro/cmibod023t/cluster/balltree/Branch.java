package nl.hro.cmibod023t.cluster.balltree;

import nl.hro.cmibod023t.cluster.points.KDPoint;

class Branch<E extends KDPoint> implements Node<E> {
	private final E value;
	private final Node<E> right;
	private final Node<E> left;

	public Branch(E value, Node<E> left, Node<E> right) {
		this.value = value;
		this.right = right;
		this.left = left;
	}

	@Override
	public Node<E> getRight() {
		return right;
	}

	@Override
	public Node<E> getLeft() {
		return left;
	}

	@Override
	public E getValue() {
		return value;
	}
}
