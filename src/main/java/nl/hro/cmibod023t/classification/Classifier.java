package nl.hro.cmibod023t.classification;

public interface Classifier<T> extends Trainable<T> {
	Result<T> test(Object... features);

	default Result<T> test(Classifiable object) {
		return test(object.getFeatures());
	}
}
