package hro.cmibod023t.cluster.points;

import hro.cmibod023t.cluster.Cluster;

public interface Point {
	double getDistance(Point p);

	void setCluster(Cluster c);

	Cluster getCluster();
}
