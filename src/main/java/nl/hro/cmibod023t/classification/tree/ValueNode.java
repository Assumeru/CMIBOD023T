package nl.hro.cmibod023t.classification.tree;

import nl.hro.cmibod023t.classification.Result;

public class ValueNode<T> implements Node<T> {
	private final Result<T> result;

	public ValueNode(T value, double probability) {
		this.result = new Result<>(value, probability);
	}

	@Override
	public Result<T> getValue(Object... values) {
		return result;
	}

	@Override
	public String toString(int indent) {
		StringBuilder sb = new StringBuilder();
		while(indent > 0) {
			sb.append('\t');
			indent--;
		}
		return sb.append("Value<").append(result).append('>').toString();
	}

	@Override
	public String toString() {
		return toString(0);
	}
}
