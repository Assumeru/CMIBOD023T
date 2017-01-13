package nl.hro.cmibod023t.classification;

public interface Classifier<T> extends Trainable<T> {
	Result<T> test(Object... features);
}
