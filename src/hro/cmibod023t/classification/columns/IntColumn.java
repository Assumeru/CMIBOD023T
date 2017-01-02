package hro.cmibod023t.classification.columns;

import java.util.Collection;

import hro.cmibod023t.classification.continuous.Range;

public class IntColumn extends AbstractColumn<Range<Integer>> {
	public IntColumn(int index) {
		super(Range.class, index);
	}

	@Override
	protected void checkType(Object feature) {
		super.checkType(feature);
		if(((Range<?>) feature).getType() != Integer.class) {
			throw new IllegalArgumentException("Not an integer range");
		}
	}

	@Override
	public void add(Object feature) {
		if(feature instanceof Integer) {
			feature = Range.is((Integer) feature);
		}
		super.add(feature);
	}

	@Override
	public Collection<Integer> getRowsMatching(Object feature) {
		if(!(feature instanceof Integer)) {
			checkType(feature);
		}
		return getIndices(feature);
	}

	@Override
	public boolean matches(Object feature, Range<Integer> row) {
		return super.matches(feature, row) || (feature instanceof Integer && row.inRange((Integer) feature));
	}
}
