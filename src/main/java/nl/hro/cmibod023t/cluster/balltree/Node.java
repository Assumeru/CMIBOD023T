package nl.hro.cmibod023t.cluster.balltree;

import java.util.Arrays;

import nl.hro.cmibod023t.cluster.points.KDPoint;

interface Node<E extends KDPoint> {
	Node<E> getRight();

	Node<E> getLeft();

	E getValue();

	static <E extends KDPoint> Node<E> build(E[] points, int dimension) {
		if(points.length == 1) {
			return new Leaf<>((E) points[0]);
		}
		Arrays.sort(points, KDPoint.getComparator(dimension));
		int median = points.length / 2;
		E point = points[median];
		dimension = (dimension + 1) % point.getDimensions();
		E[] leftP = Arrays.copyOfRange(points, 0, median);
		Node<E> left = leftP.length > 0 ? build(leftP, dimension) : null;
		E[] rightP = Arrays.copyOfRange(points, median + 1, points.length);
		Node<E> right = rightP.length > 0 ? build(rightP, dimension) : null;
		return new Branch<>(point, left, right);
	}
}
