package nl.hro.cmibod023t.test.adult;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import nl.hro.cmibod023t.classification.Classifier;
import nl.hro.cmibod023t.classification.DecisionTree;
import nl.hro.cmibod023t.exercises.Assignment1;

public class AdultTest {
	public static void main(String[] args) throws IOException {
		List<Person> people = new PersonParser(new File(args[0])).parsePeople();
		Set<Person> subset = Assignment1.subset(people);
		Classifier<String> classifier = new DecisionTree<>(String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class);
		for(Person p : subset) {
			classifier.train(p.getIncome(), getFeatures(p));
		}
		double correct = 0;
		int line = 0;
		for(Person p : people) {
			line++;
			try {
				if(p.getIncome().equals(classifier.test(getFeatures(p)).getValue())) {
					correct++;
				}
			} catch(Exception e) {
				System.err.println("Error on line " + line + ": " + e.getMessage());
			}
		}
		System.out.println(correct / people.size());
		//System.out.println(classifier);
	}

	private static Object[] getFeatures(Person p) {
		return new Object[] {
				p.getWorkClass(), p.getEducation(), p.getMaritalStatus(), p.getOccupation(), p.getRelationship(), p.getRace(), p.getSex(), p.getNativeCountry()
		};
	}
}
