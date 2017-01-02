package hro.cmibod023t.test;

import hro.cmibod023t.classification.Classifier;
import hro.cmibod023t.classification.Result;

public class TrainingSet {
	public static void train(Classifier<Play> classifier) {
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
	}

	public static Result<Play> test(Classifier<Play> classifier) {
		return classifier.test(Outlook.SUNNY, Temperature.COOL, Humidity.HIGH, Wind.STRONG);
	}

	public enum Play {
		INDOOR, OUTDOOR
	}

	public enum Outlook {
		SUNNY, OVERCAST, RAIN
	}

	public enum Temperature {
		HOT, MILD, COOL
	}

	public enum Humidity {
		HIGH, NORMAL
	}

	public enum Wind {
		WEAK, STRONG
	}
}
