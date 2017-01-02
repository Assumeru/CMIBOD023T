package hro.cmibod023t.classification.tree;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import hro.cmibod023t.classification.Result;
import hro.cmibod023t.classification.columns.Column;

public class BranchNode<T> implements Node<T> {
	private final Map<Object, Node<T>> children;
	private final Column<Object> column;

	@SuppressWarnings("unchecked")
	public BranchNode(Map<Object, Node<T>> children, Column<?> column) {
		this.children = children;
		this.column = (Column<Object>) column.copy(Collections.emptySet());
	}

	@Override
	public Result<T> getValue(Object... values) {
		Object feature = values[column.getIndex()];
		Node<T> child = children.get(feature);
		if(child == null) {
			for(Entry<Object, Node<T>> entry : children.entrySet()) {
				if(column.matches(feature, entry.getKey())) {
					child = entry.getValue();
					break;
				}
			}
		}
		if(child != null) {
			return child.getValue(values);
		}
		throw new IllegalArgumentException("Failed to match " + feature + " in column " + column.getIndex());
	}

	@Override
	public String toString(int indent) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < indent; i++) {
			sb.append('\t');
		}
		String indentation = sb.toString();
		sb.append("Branch<").append(column.getIndex()).append(",\n");
		for(Entry<Object, Node<T>> entry : children.entrySet()) {
			sb.append(indentation).append('\t').append(entry.getKey()).append(":\n").append(entry.getValue().toString(indent + 2)).append('\n');
		}
		return sb.append(indentation).append('>').toString();
	}

	@Override
	public String toString() {
		return toString(0);
	}
}
