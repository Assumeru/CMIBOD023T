package nl.hro.cmibod023t.cluster.points;

import nl.hro.cmibod023t.cluster.Cluster;

public interface Point {
	double getDistance(Point p);

	int getDimensions();

	void setCluster(Cluster<?> c);

	Cluster<?> getCluster();
}
