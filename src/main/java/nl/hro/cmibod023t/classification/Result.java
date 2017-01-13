package nl.hro.cmibod023t.classification;

public class Result<T> {
	private final T value;
	private final double probability;

	public Result(T value, double probability) {
		this.value = value;
		this.probability = probability;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + " " + probability;
	}
}
