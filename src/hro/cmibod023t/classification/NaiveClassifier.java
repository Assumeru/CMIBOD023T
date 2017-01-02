package hro.cmibod023t.classification;

import java.util.Map;
import java.util.Map.Entry;

import hro.cmibod023t.collection.DoubleCountMap;

public class NaiveClassifier<T> implements Classifier<T> {
	private final FeatureTable<T> table;

	public NaiveClassifier(Class<?>... columns) {
		table = new FeatureTable<>(columns);
	}

	@Override
	public NaiveClassifier<T> train(T result, Object... features) {
		table.train(result, features);
		return this;
	}

	@Override
	public Result<T> test(Object... features) {
		table.checkFeatureLength(features);
		Map<T, Integer> results = table.countResults();
		DoubleCountMap<T> probabilities = initMap(results);
		for(int i = 0; i < features.length; i++) {
			Iterable<Integer> indices = table.getColumn(i).getRowsMatching(features[i]);
			Map<T, Integer> featureCount = table.countResults(indices);
			for(Entry<T, Integer> entry : featureCount.entrySet()) {
				double p = entry.getValue();
				probabilities.add(entry.getKey(), Math.log(p / results.get(entry.getKey())));
			}
		}
		return getBest(probabilities);
	}

	private DoubleCountMap<T> initMap(Map<T, Integer> results) {
		DoubleCountMap<T> probabilities = new DoubleCountMap<>();
		for(Entry<T, Integer> entry : results.entrySet()) {
			probabilities.put(entry.getKey(), Math.log(entry.getValue() / (double) table.size()));
		}
		return probabilities;
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
