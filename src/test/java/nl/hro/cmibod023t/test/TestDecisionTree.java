package nl.hro.cmibod023t.test;

import org.junit.Test;

import nl.hro.cmibod023t.classification.DecisionTree;

public class TestDecisionTree extends TrainingSet {
	@Test
	public void test() {
		DecisionTree<Play> classifier = new DecisionTree<>(Outlook.class, Temperature.class, Humidity.class, Wind.class);
		train(classifier);
		System.out.println(classifier);
		System.out.println(test(classifier));
	}
}
