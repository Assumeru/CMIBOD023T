package nl.hro.cmibod023t.classification;

import nl.hro.cmibod023t.classification.tree.Node;

public class DecisionTree<T> implements Classifier<T> {
	private final FeatureTable<T> table;
	private Node<T> tree;

	public DecisionTree(Class<?>... columns) {
		table = new FeatureTable<>(columns);
	}

	@Override
	public DecisionTree<T> train(T result, Object... features) {
		table.train(result, features);
		tree = null;
		return this;
	}

	@Override
	public Result<T> test(Object... features) {
		table.checkFeatureLength(features);
		if(tree == null) {
			tree = Node.build(table);
		}
		return tree.getValue(features);
	}

	@Override
	public String toString() {
		if(tree == null) {
			tree = Node.build(table);
		}
		return tree.toString();
	}
}
