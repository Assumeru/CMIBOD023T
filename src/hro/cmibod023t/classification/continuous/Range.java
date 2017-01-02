package hro.cmibod023t.classification.continuous;

public interface Range<T extends Number> {
	boolean inRange(T value);

	Class<T> getType();

	public static Range<Integer> lessThan(int value) {
		return new IntRange.LessThan(value);
	}

	public static Range<Integer> moreThan(int value) {
		return new IntRange.MoreThan(value);
	}

	public static Range<Integer> create(int min, int max) {
		return new IntRange.InRange(min, max);
	}

	public static Range<Integer> is(int value) {
		return new IntRange.EqualTo(value);
	}
}
