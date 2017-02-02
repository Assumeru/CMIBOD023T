package nl.hro.cmibod023t.collection;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetList<T> extends AbstractList<T> {
	private final List<T> list;
	private final Set<T> set;

	public SetList() {
		list = new ArrayList<>();
		set = new HashSet<>();
	}

	public SetList(Collection<? extends T> c) {
		this();
		addAll(c);
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void add(int index, T element) {
		if(!set.contains(element)) {
			set.add(element);
			list.add(index, element);
		}
	}

	@Override
	public boolean add(T e) {
		if(set.contains(e)) {
			return false;
		}
		set.add(e);
		return list.add(e);
	}
}
