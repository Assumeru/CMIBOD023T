package hro.cmibod023t.test;

import hro.cmibod023t.classification.DecisionTree;
import hro.cmibod023t.test.TrainingSet.Humidity;
import hro.cmibod023t.test.TrainingSet.Outlook;
import hro.cmibod023t.test.TrainingSet.Play;
import hro.cmibod023t.test.TrainingSet.Temperature;
import hro.cmibod023t.test.TrainingSet.Wind;

public class TestDecisionTree {
	public static void main(String[] args) {
		DecisionTree<Play> classifier = new DecisionTree<>(Outlook.class, Temperature.class, Humidity.class, Wind.class);
		TrainingSet.train(classifier);
		System.out.println(classifier);
		System.out.println(TrainingSet.test(classifier));
	}
}
