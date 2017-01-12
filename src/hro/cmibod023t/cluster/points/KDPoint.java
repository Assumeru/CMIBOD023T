package hro.cmibod023t.cluster.points;

import java.util.Comparator;

public interface KDPoint extends Point {
	default int compareTo(KDPoint other, int dimension) {
		return Double.compare(getDimension(dimension), other.getDimension(dimension));
	}

	double getDimension(int dimension);

	static <E extends KDPoint> Comparator<E> getComparator(int dimension) {
		return (o1, o2) -> o1.compareTo(o2, dimension);
	}
}
