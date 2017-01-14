package nl.hro.cmibod023t.classification;

import java.util.Collections;
import java.util.Map;

import nl.hro.cmibod023t.collection.IntCountMap;

public class CachingFeatureTable<T> extends FeatureTable<T> {
	private final IntCountMap<T> resultCount;
	private final Map<T, Integer> resultCountWrapper;

	public CachingFeatureTable(Class<?>... columns) {
		super(columns);
		resultCount = new IntCountMap<>();
		resultCountWrapper = Collections.unmodifiableMap(resultCount);
	}

	@Override
	public FeatureTable<T> train(T result, Object... features) {
		resultCount.increment(result);
		return super.train(result, features);
	}

	@Override
	public Map<T, Integer> countResults() {
		return resultCountWrapper;
	}

	@Override
	protected IntCountMap<T> buildResultCounter() {
		IntCountMap<T> count = new IntCountMap<>();
		for(T result : resultCount.keySet()) {
			count.add(result, 0);
		}
		return count;
	}
}
