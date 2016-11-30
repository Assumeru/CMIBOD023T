package hro.cmibod023t.bayes.columns;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractColumn<T> implements Column {
	private final Class<?> type;
	private final List<T> features;

	public AbstractColumn(Class<?> type) {
		this(type, new ArrayList<>());
	}

	public AbstractColumn(Class<?> type, List<T> features) {
		this.type = type;
		this.features = features;
	}

	protected void checkType(Object feature) {
		if(feature.getClass() != type && !type.isAssignableFrom(feature.getClass())) {
			throw new IllegalArgumentException("Unexpected feature of type " + feature.getClass() + " expected one of " + type);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(Object feature) {
		checkType(feature);
		features.add((T) feature);
	}

	@Override
	public List<Integer> getRowsMatching(Object feature) {
		checkType(feature);
		return getIndices(feature);
	}

	protected List<Integer> getIndices(Object feature) {
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < features.size(); i++) {
			if(matches(feature, features.get(i))) {
				indices.add(i);
			}
		}
		return indices;
	}

	protected boolean matches(Object feature, T row) {
		return Objects.equals(feature, row);
	}
}
