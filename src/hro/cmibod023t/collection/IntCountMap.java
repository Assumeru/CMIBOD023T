package hro.cmibod023t.collection;

import java.util.HashMap;

public class IntCountMap<T> extends MapWrapper<T, Integer> {
	public IntCountMap() {
		super(new HashMap<>());
	}

	public void add(T key, int amount) {
		Integer value = get(key);
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
