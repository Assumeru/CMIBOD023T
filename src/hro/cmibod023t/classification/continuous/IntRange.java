package hro.cmibod023t.classification.continuous;

public abstract class IntRange implements Range<Integer> {
	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	static class LessThan extends IntRange {
		private final int value;

		public LessThan(int value) {
			this.value = value;
		}

		@Override
		public boolean inRange(Integer value) {
			return value < this.value;
		}

		@Override
		public String toString() {
			return "< " + value;
		}

		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			} else if(obj instanceof LessThan) {
				return value == ((LessThan) obj).value;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return 599 * value;
		}
	}

	static class MoreThan extends IntRange {
		private final int value;

		public MoreThan(int value) {
			this.value = value;
		}

		@Override
		public boolean inRange(Integer value) {
			return value > this.value;
		}

		@Override
		public String toString() {
			return "> " + value;
		}

		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			} else if(obj instanceof MoreThan) {
				return value == ((MoreThan) obj).value;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return 601 * value;
		}
	}

	static class InRange extends IntRange {
		private final int min;
		private final int max;

		public InRange(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public boolean inRange(Integer value) {
			return value >= min && value <= max;
		}

		@Override
		public String toString() {
			return min + " - " + max;
		}

		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			} else if(obj instanceof InRange) {
				InRange other = (InRange) obj;
				return min == other.min && max == other.max;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return 607 * min + max;
		}
	}

	static class EqualTo extends IntRange {
		private final int value;

		public EqualTo(int value) {
			this.value = value;
		}

		@Override
		public boolean inRange(Integer value) {
			return value == this.value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			} else if(obj instanceof EqualTo) {
				return value == ((EqualTo) obj).value;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return 613 * value;
		}
	}
}
