package nl.hro.cmibod023t.classification.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nl.hro.cmibod023t.classification.FeatureTable;
import nl.hro.cmibod023t.classification.Result;
import nl.hro.cmibod023t.classification.columns.Column;

public interface Node<T> {
	Result<T> getValue(Object... values);

	String toString(int indent);

	static <T> Node<T> build(FeatureTable<T> table) {
		Map<T, Integer> results = table.countResults();
		if(results.keySet().size() == 1) {
			return new ValueNode<>(results.keySet().iterator().next(), 1);
		}
		Column<?> column;
		if(table.columns().size() == 1) {
			column = table.getColumn(0);
		} else {
			column = table.getColumnWithLargestGain();
		}
		Set<Object> features = column.getFeatures();
		if(features.size() == 1) {
			int max = Integer.MIN_VALUE;
			T result = null;
			double total = 0;
			for(Entry<T, Integer> entry : results.entrySet()) {
				int value = entry.getValue();
				total += value;
				if(value > max) {
					max = value;
					result = entry.getKey();
				}
			}
			return new ValueNode<>(result, max / total);
		}
		Map<Object, Node<T>> children = new HashMap<>();
		for(Object feature : features) {
			Node<T> child = build(table.subset(column, feature));
			children.put(feature, child);
		}
		return new BranchNode<>(children, column);
	}
}
