package hro.cmibod023t.classification.columns;

import java.util.Collection;
import java.util.Set;

public interface Column<T> {
	void add(Object feature);

	Collection<Integer> getRowsMatching(Object feature);

	Set<Object> getFeatures();

	Column<?> copy(Iterable<Integer> indices);

	int getIndex();

	boolean matches(Object external, T internal);

	static Column<?> create(Class<?> type, int index) {
		if(type == boolean.class || type == Boolean.class) {
			return new BooleanColumn(index);
		} else if(type == int.class || type == Integer.class) {
			return new IntColumn(index);
		} else if(Enum.class.isAssignableFrom(type)) {
			return new EnumColumn(type, index);
		}
		throw new UnsupportedOperationException("Unsupported type: " + type);
	}
}
