package nl.hro.cmibod023t.collection;

import java.util.HashMap;

public class DoubleCountMap<T> extends MapWrapper<T, Double> {
	public DoubleCountMap() {
		super(new HashMap<>());
	}

	public void add(T key, double amount) {
		Double value = get(key);
		if(value == null) {
			put(key, amount);
		} else {
			put(key, value + amount);
		}
	}

	public void increment(T key) {
		add(key, 1);
	}
}
