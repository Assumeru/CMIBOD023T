package hro.cmibod023t.bayes.columns;

import java.util.List;

public interface Column {
	void add(Object feature);

	List<Integer> getRowsMatching(Object feature);

	static Column create(Class<?> type) {
		if(type == boolean.class || type == Boolean.class) {
			return new BooleanColumn();
		} else if(type == int.class || type == Integer.class) {
			return new IntColumn();
		} else if(Enum.class.isAssignableFrom(type)) {
			return new EnumColumn(type);
		}
		throw new UnsupportedOperationException("Unsupported type: " + type);
	}
}
