package hro.cmibod023t.cluster.points;

public class EuclidianDoublePoint extends AbstractPoint<EuclidianDoublePoint> implements KDPoint {
	protected final double[] dimensions;

	public EuclidianDoublePoint(double... dimensions) {
		this.dimensions = dimensions;
	}

	@Override
	protected double getDistanceInternal(EuclidianDoublePoint p) {
		double sum = 0;
		if(dimensions.length != p.dimensions.length) {
			throw new IllegalArgumentException(this + " cannot be compared to " + p);
		}
		for(int i = 0; i < dimensions.length; i++) {
			double delta = dimensions[i] - p.dimensions[i];
			sum += delta * delta;
		}
		return sum;
	}

	@Override
	public int getDimensions() {
		return dimensions.length;
	}

	@Override
	public double getDimension(int dimension) {
		return dimensions[dimension];
	}
}
