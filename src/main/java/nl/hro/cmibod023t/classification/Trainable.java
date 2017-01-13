package nl.hro.cmibod023t.classification;

public interface Trainable<T> {
	Trainable<T> train(T result, Object... features);
}
