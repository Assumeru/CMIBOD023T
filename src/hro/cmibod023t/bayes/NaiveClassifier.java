package hro.cmibod023t.bayes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hro.cmibod023t.bayes.columns.Column;
import hro.cmibod023t.collection.DoubleCountMap;
import hro.cmibod023t.collection.IntCountMap;

public class NaiveClassifier<T> {
	private final List<T> results;
	private final Column[] columns;

	public NaiveClassifier(Class<?>... columns) {
		if(columns.length < 1) {
			throw new IllegalArgumentException("Need at least one feature");
		}
		this.results = new ArrayList<>();
		this.columns = new Column[columns.length];
		for(int i = 0; i < columns.length; i++) {
			this.columns[i] = Column.create(columns[i]);
		}
	}

	private void checkFeatureLength(Object[] features) {
		if(features == null || features.length != columns.length) {
			throw new IllegalArgumentException("Need " + columns.length + " features");
		}
	}

	public NaiveClassifier<T> train(T result, Object... features) {
		checkFeatureLength(features);
		for(int i = 0; i < features.length; i++) {
			columns[i].add(features[i]);
		}
		results.add(result);
		return this;
	}

	public Result<T> test(Object... features) {
		checkFeatureLength(features);
		IntCountMap<T> results = countResults();
		DoubleCountMap<T> probabilities = initMap(results);
		for(int i = 0; i < features.length; i++) {
			List<Integer> indices = columns[i].getRowsMatching(features[i]);
			IntCountMap<T> featureCount = countFeatures(indices);
			for(Entry<T, Integer> entry : featureCount.entrySet()) {
				double p = entry.getValue();
				probabilities.add(entry.getKey(), Math.log(p / results.get(entry.getKey())));
			}
		}
		return getBest(probabilities);
	}

	private IntCountMap<T> countResults() {
		IntCountMap<T> count = new IntCountMap<>();
		for(T result : results) {
			count.increment(result);
		}
		return count;
	}

	private DoubleCountMap<T> initMap(IntCountMap<T> results) {
		DoubleCountMap<T> probabilities = new DoubleCountMap<>();
		for(Entry<T, Integer> entry : results.entrySet()) {
			probabilities.put(entry.getKey(), Math.log(entry.getValue() / (double) this.results.size()));
		}
		return probabilities;
	}

	private IntCountMap<T> countFeatures(List<Integer> indices) {
		IntCountMap<T> count = new IntCountMap<>();
		for(T result : results) {
			count.add(result, 0);
		}
		for(Integer i : indices) {
			count.increment(results.get(i));
		}
		return count;
	}

	private Result<T> getBest(Map<T, Double> probabilities) {
		Entry<T, Double> best = probabilities.entrySet().iterator().next();
		for(Entry<T, Double> entry : probabilities.entrySet()) {
			if(entry.getValue() > best.getValue()) {
				best = entry;
			}
		}
		return new Result<>(best.getKey(), Math.exp(best.getValue()));
	}
}
