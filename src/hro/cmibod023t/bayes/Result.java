package hro.cmibod023t.bayes;

public class Result<T> {
	private final T value;
	private final double probability;

	public Result(T value, double probability) {
		this.value = value;
		this.probability = probability;
	}

	@Override
	public String toString() {
		return value + " " + probability;
	}
}
