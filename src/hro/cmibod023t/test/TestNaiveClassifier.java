package hro.cmibod023t.test;

import hro.cmibod023t.bayes.NaiveClassifier;

public class TestNaiveClassifier {
	public static void main(String[] args) {
		NaiveClassifier<Play> classifier = new NaiveClassifier<>(Outlook.class, Temperature.class, Humidity.class, Wind.class);
		classifier
			.train(Play.INDOOR, Outlook.SUNNY, Temperature.HOT, Humidity.HIGH, Wind.WEAK)
			.train(Play.INDOOR, Outlook.SUNNY, Temperature.HOT, Humidity.HIGH, Wind.STRONG)
			.train(Play.OUTDOOR, Outlook.OVERCAST, Temperature.HOT, Humidity.HIGH, Wind.WEAK)
			.train(Play.OUTDOOR, Outlook.RAIN, Temperature.MILD, Humidity.HIGH, Wind.WEAK)
			.train(Play.OUTDOOR, Outlook.RAIN, Temperature.COOL, Humidity.NORMAL, Wind.WEAK)
			.train(Play.INDOOR, Outlook.RAIN, Temperature.COOL, Humidity.NORMAL, Wind.STRONG)
			.train(Play.OUTDOOR, Outlook.OVERCAST, Temperature.COOL, Humidity.NORMAL, Wind.STRONG)
			.train(Play.INDOOR, Outlook.SUNNY, Temperature.MILD, Humidity.HIGH, Wind.WEAK)
			.train(Play.OUTDOOR, Outlook.SUNNY, Temperature.COOL, Humidity.NORMAL, Wind.WEAK)
			.train(Play.OUTDOOR, Outlook.RAIN, Temperature.MILD, Humidity.NORMAL, Wind.WEAK)
			.train(Play.OUTDOOR, Outlook.SUNNY, Temperature.MILD, Humidity.NORMAL, Wind.STRONG)
			.train(Play.OUTDOOR, Outlook.OVERCAST, Temperature.MILD, Humidity.HIGH, Wind.STRONG)
			.train(Play.OUTDOOR, Outlook.OVERCAST, Temperature.HOT, Humidity.NORMAL, Wind.WEAK)
			.train(Play.INDOOR, Outlook.RAIN, Temperature.MILD, Humidity.HIGH, Wind.STRONG);
		System.out.println(classifier.test(Outlook.SUNNY, Temperature.COOL, Humidity.HIGH, Wind.STRONG));
	}

	private enum Play {
		INDOOR, OUTDOOR
	}

	private enum Outlook {
		SUNNY, OVERCAST, RAIN
	}

	private enum Temperature {
		HOT, MILD, COOL
	}

	private enum Humidity {
		HIGH, NORMAL
	}

	private enum Wind {
		WEAK, STRONG
	}
}
