package hro.cmibod023t.cluster.balltree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hro.cmibod023t.cluster.points.KDPoint;

public class BallTree<E extends KDPoint> {
	private final Node<E> root;

	@SuppressWarnings("unchecked")
	public BallTree(Collection<E> collection) {
		root = (Node<E>) Node.build(collection.toArray(new KDPoint[collection.size()]), 0);
	}

	public Collection<E> getNeighbours(E point, double epsilon, double epsilonSq) {
		return getNeighbours(root, point, epsilon, epsilonSq, 0);
	}

	private Collection<E> getNeighbours(Node<E> node, E point, double epsilon, double epsilonSq, int dimension) {
		List<E> neighbours = new ArrayList<>();
		while(node != null) {
			double dimN = node.getValue().getDimension(dimension);
			double dimP = point.getDimension(dimension);
			dimension = (dimension + 1) % point.getDimensions();
			if(dimN + epsilon >= dimP && dimN - epsilon <= dimP) {
				if(node.getValue().getDistance(point) <= epsilonSq) {
					neighbours.add(node.getValue());
				}
				neighbours.addAll(getNeighbours(node.getLeft(), point, epsilon, epsilonSq, dimension));
				node = node.getRight();
			} else if(dimN < dimP) {
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
		}
		return neighbours;
	}
}
