package nl.hro.cmibod023t.test;

import org.junit.Test;

import nl.hro.cmibod023t.classification.NaiveClassifier;

public class TestNaiveClassifier extends TrainingSet {
	@Test
	public void test() {
		NaiveClassifier<Play> classifier = new NaiveClassifier<>(Outlook.class, Temperature.class, Humidity.class, Wind.class);
		train(classifier);
		System.out.println(test(classifier));
	}
}
