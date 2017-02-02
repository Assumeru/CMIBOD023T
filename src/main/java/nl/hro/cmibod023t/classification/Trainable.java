package nl.hro.cmibod023t.classification;

public interface Trainable<T> {
	Trainable<T> train(T result, Object... features);

	default Trainable<T> train(Testable<T> object) {
		return train(object.getTargetClass(), object.getFeatures());
	}
}
