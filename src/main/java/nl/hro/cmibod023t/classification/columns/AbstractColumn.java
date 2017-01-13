package nl.hro.cmibod023t.classification.columns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractColumn<T> implements Column<T> {
	private final Class<?> type;
	private final List<T> features;
	private final int index;

	public AbstractColumn(Class<?> type, int index) {
		this.type = type;
		this.features = new ArrayList<>();
		this.index = index;
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
	public Collection<Integer> getRowsMatching(Object feature) {
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

	@Override
	public boolean matches(Object feature, T row) {
		return Objects.equals(feature, row);
	}

	@Override
	public Set<Object> getFeatures() {
		return new HashSet<>(features);
	}

	@Override
	public Column<?> copy(Iterable<Integer> indices) {
		Column<?> copy = Column.create(type, index);
		for(Integer i : indices) {
			copy.add(features.get(i));
		}
		return copy;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "<Column[" + type + ", " + index + "] " + features + ">";
	}
}
