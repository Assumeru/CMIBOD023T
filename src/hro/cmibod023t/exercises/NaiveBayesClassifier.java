package hro.cmibod023t.exercises;

import hro.cmibod023t.bayes.NaiveClassifier;
import hro.cmibod023t.bayes.Result;
import hro.cmibod023t.bayes.continuous.Range;

public class NaiveBayesClassifier {
	public static void main(String[] args) {
		exercise1();
		exercise2();
	}

	private static void exercise1() {
		System.out.println("Excercise 1");
		NaiveClassifier<Boolean> classifier = new NaiveClassifier<>(int.class, Income.class, boolean.class, CreditRating.class);
		classifier
			.train(false, Range.lessThan(30),   Income.HIGH,   false, CreditRating.FAIR)
			.train(false, Range.lessThan(30),   Income.HIGH,   false, CreditRating.EXCELLENT)
			.train(true,  Range.create(30, 40), Income.HIGH,   false, CreditRating.FAIR)
			.train(true,  Range.moreThan(40),   Income.MEDIUM, false, CreditRating.FAIR)
			.train(true,  Range.moreThan(40),   Income.LOW,    true,  CreditRating.FAIR)
			.train(false, Range.moreThan(40),   Income.LOW,    true,  CreditRating.EXCELLENT)
			.train(true,  Range.create(30, 40), Income.LOW,    true,  CreditRating.EXCELLENT)
			.train(false, Range.lessThan(30),   Income.MEDIUM, false, CreditRating.FAIR)
			.train(true,  Range.lessThan(30),   Income.LOW,    true,  CreditRating.FAIR)
			.train(true,  Range.moreThan(40),   Income.MEDIUM, true,  CreditRating.FAIR)
			.train(true,  Range.lessThan(30),   Income.MEDIUM, true,  CreditRating.EXCELLENT)
			.train(true,  Range.create(30, 40), Income.MEDIUM, false, CreditRating.EXCELLENT)
			.train(true,  Range.create(30, 40), Income.HIGH,   true,  CreditRating.FAIR)
			.train(false, Range.moreThan(40),   Income.MEDIUM, false, CreditRating.EXCELLENT);
		Result<Boolean> x = classifier.test(21, Income.MEDIUM, true, CreditRating.FAIR);
		Result<Boolean> y = classifier.test(35, Income.LOW, false, CreditRating.EXCELLENT);
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
	}

	private static void exercise2() {
		System.out.println("Excercise 2");
		NaiveClassifier<Status> classifier = new NaiveClassifier<>(Department.class, int.class, int.class);
		classifier
			.train(Status.SENIOR, Department.SALES,     Range.create(31, 35), Range.create(46000, 50000))
			.train(Status.JUNIOR, Department.SALES,     Range.create(26, 30), Range.create(26000, 30000))
			.train(Status.JUNIOR, Department.SALES,     Range.create(31, 35), Range.create(31000, 35000))
			.train(Status.JUNIOR, Department.SYSTEMS,   Range.create(21, 25), Range.create(46000, 50000))
			.train(Status.JUNIOR, Department.SYSTEMS,   Range.create(31, 35), Range.create(66000, 70000))
			.train(Status.JUNIOR, Department.SYSTEMS,   Range.create(26, 30), Range.create(46000, 50000))
			.train(Status.SENIOR, Department.SYSTEMS,   Range.create(41, 45), Range.create(66000, 70000))
			.train(Status.SENIOR, Department.MARKETING, Range.create(36, 40), Range.create(46000, 50000))
			.train(Status.JUNIOR, Department.MARKETING, Range.create(31, 35), Range.create(41000, 45000))
			.train(Status.SENIOR, Department.SECRETARY, Range.create(46, 50), Range.create(36000, 40000))
			.train(Status.JUNIOR, Department.SECRETARY, Range.create(26, 30), Range.create(26000, 30000));
		Result<Status> x = classifier.test(Department.MARKETING, Range.create(31, 35), Range.create(46000, 50000));
		Result<Status> y = classifier.test(Department.SALES, Range.create(31, 35), Range.create(66000, 70000));
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
	}

	private enum Income {
		LOW, MEDIUM, HIGH
	}

	private enum CreditRating {
		FAIR, EXCELLENT
	}

	private enum Department {
		SALES, SYSTEMS, MARKETING, SECRETARY
	}

	private enum Status {
		JUNIOR, SENIOR
	}
}
