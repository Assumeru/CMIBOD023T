package nl.hro.cmibod023t.classification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.hro.cmibod023t.classification.columns.Column;
import nl.hro.cmibod023t.collection.IntCountMap;

public class FeatureTable<T> implements Trainable<T> {
	private static final double LN2 = Math.log(2);
	private final List<T> results;
	private final Column<?>[] columns;

	public FeatureTable(Class<?>... columns) {
		if(columns.length < 1) {
			throw new IllegalArgumentException("Need at least one feature");
		}
		this.results = new ArrayList<>();
		this.columns = new Column[columns.length];
		for(int i = 0; i < columns.length; i++) {
			this.columns[i] = Column.create(columns[i], i);
		}
	}

	private FeatureTable(Column<?>[] columns, List<T> results) {
		this.columns = columns;
		this.results = results;
	}

	private static double log2(double value) {
		return Math.log(value) / LN2;
	}

	public void checkFeatureLength(Object[] features) {
		if(features == null || features.length != columns.length) {
			throw new IllegalArgumentException("Need " + columns.length + " features");
		}
	}

	@Override
	public FeatureTable<T> train(T result, Object... features) {
		checkFeatureLength(features);
		for(int i = 0; i < features.length; i++) {
			columns[i].add(features[i]);
		}
		results.add(result);
		return this;
	}

	public Column<?> getColumn(int i) {
		return columns[i];
	}

	public Map<T, Integer> countResults() {
		IntCountMap<T> count = new IntCountMap<>();
		for(T result : results) {
			count.increment(result);
		}
		return count;
	}

	public Map<T, Integer> countResults(Iterable<Integer> indices) {
		IntCountMap<T> count = new IntCountMap<>();
		for(T result : results) {
			count.add(result, 0);
		}
		for(Integer i : indices) {
			count.increment(results.get(i));
		}
		return count;
	}

	public int size() {
		return results.size();
	}

	public Collection<Column<?>> columns() {
		return Arrays.asList(columns);
	}

	public FeatureTable<T> subset(Column<?> column, Object feature) {
		Collection<Integer> indices = column.getRowsMatching(feature);
		Column<?>[] newColumns = new Column[columns.length - 1];
		List<T> newResults = new ArrayList<>(indices.size());
		int i = 0;
		for(Column<?> c : columns) {
			if(c != column) {
				newColumns[i++] = c.copy(indices);
			}
		}
		for(Integer index : indices) {
			newResults.add(results.get(index));
		}
		return new FeatureTable<>(newColumns, newResults);
	}

	private double getEntropy() {
		Map<T, Integer> count = countResults();
		double entropy = 0;
		double size = results.size();
		for(Integer i : count.values()) {
			double probability = i / size;
			entropy += probability * log2(probability);
		}
		return -entropy;
	}

	private double getEntropy(Collection<Integer> indices) {
		IntCountMap<T> count = new IntCountMap<>();
		for(Integer i : indices) {
			count.increment(results.get(i));
		}
		double entropy = 0;
		double total = indices.size();
		for(Integer i : count.values()) {
			if(i > 0) {
				double probability = i / total;
				entropy += probability * log2(probability);
			}
		}
		return -entropy;
	}

	private double getGainSum(Column<?> column) {
		Set<Object> values = column.getFeatures();
		double sum = 0;
		double size = results.size();
		for(Object value : values) {
			Collection<Integer> occurences = column.getRowsMatching(value);
			sum += occurences.size() / size * getEntropy(occurences);
		}
		return sum;
	}

	public Column<?> getColumnWithLargestGain() {
		double entropy = getEntropy();
		double max = Double.NEGATIVE_INFINITY;
		Column<?> split = null;
		for(Column<?> column : columns()) {
			double gain = entropy - getGainSum(column);
			if(gain > max) {
				max = gain;
				split = column;
			}
		}
		return split;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("<FeatureTable ").append(results);
		for(Column<?> c : columns) {
			sb.append('\n').append(c);
		}
		return sb.append('>').toString();
	}
}
