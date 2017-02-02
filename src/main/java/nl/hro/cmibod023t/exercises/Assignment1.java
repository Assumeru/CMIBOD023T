package nl.hro.cmibod023t.exercises;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import nl.hro.cmibod023t.classification.Classifier;
import nl.hro.cmibod023t.classification.Result;

public class Assignment1 {
	private static final long SEED = 1;

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
		if(args.length == 0) {
			System.err.println("Missing file path argument");
			System.exit(1);
		}
		File file = new File(args[0]);
		List<Mushroom> mushrooms = new MushroomParser(file).parseMushrooms();
		Set<Mushroom> subset = subset(mushrooms);
		System.out.println("Subset size: " + subset.size());
		System.out.println("Naive Bayesian Classifier");
		testClassifier(subset, mushrooms, Mushroom.createNaiveBayesianClassifier());
		System.out.println("Decision Tree Classifier");
		Classifier<Mushroom.Class> tree = Mushroom.createDecisionTree();
		testClassifier(subset, mushrooms, tree);
		System.out.println(tree);
	}

	private static void testClassifier(Set<Mushroom> subset, List<Mushroom> mushrooms, Classifier<Mushroom.Class> classifier) {
		for(Mushroom mushroom : subset) {
			classifier.train(mushroom);
		}
		double correct = 0;
		for(Mushroom mushroom : mushrooms) {
			Result<Mushroom.Class> result = classifier.test(mushroom);
			if(result.getValue() == mushroom.getType()) {
				correct++;
			}
		}
		System.out.println("Accuracy: " + (correct / mushrooms.size()));
	}

	public static <E> Set<E> subset(List<E> mushrooms) {
		int size = mushrooms.size() / 3;
		Set<E> subset = new HashSet<>(size);
		Random random = new Random(SEED);
		for(int i = 0; i < size; i++) {
			int index = random.nextInt(mushrooms.size());
			E mushroom = mushrooms.get(index);
			while(subset.contains(mushroom)) {
				index = (index + 1) % mushrooms.size();
				mushroom = mushrooms.get(index);
			}
			subset.add(mushroom);
		}
		return subset;
	}

	private Assignment1() {
	}
}
