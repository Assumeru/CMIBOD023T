package nl.hro.cmibod023t.exercises;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MushroomParser {
	private static final Map<String, CharColumnParser> COLUMNS = new HashMap<>();
	static {
		COLUMNS.put("class", c -> getEnum(c, Mushroom.Class.values()));
		COLUMNS.put("cap-shape", c -> getEnum(c, Mushroom.CapShape.values()));
		COLUMNS.put("cap-surface", c -> getEnum(c, Mushroom.Surface.values()));
		COLUMNS.put("cap-color", c -> getEnum(c, Mushroom.Color.values()));
		COLUMNS.put("bruises", c -> c == 't');
		COLUMNS.put("odor", c -> getEnum(c, Mushroom.Odor.values()));
		COLUMNS.put("gill-attachment", c -> getEnum(c, Mushroom.GillAttachment.values()));
		COLUMNS.put("gill-spacing", c -> getEnum(c, Mushroom.GillSpacing.values()));
		COLUMNS.put("gill-size", c -> getEnum(c, Mushroom.GillSize.values()));
		COLUMNS.put("gill-color", c -> getEnum(c, Mushroom.Color.values()));
		COLUMNS.put("stalk-shape", c -> getEnum(c, Mushroom.StalkShape.values()));
		COLUMNS.put("stalk-root", c -> getEnum(c, Mushroom.StalkRoot.values()));
		COLUMNS.put("stalk-surface-above-ring", c -> getEnum(c, Mushroom.Surface.values()));
		COLUMNS.put("stalk-surface-below-ring", c -> getEnum(c, Mushroom.Surface.values()));
		COLUMNS.put("stalk-color-above-ring", c -> getEnum(c, Mushroom.Color.values()));
		COLUMNS.put("stalk-color-below-ring", c -> getEnum(c, Mushroom.Color.values()));
		COLUMNS.put("veil-type", c -> getEnum(c, Mushroom.VeilType.values()));
		COLUMNS.put("veil-color", c -> getEnum(c, Mushroom.Color.values()));
		COLUMNS.put("ring-number", c -> getEnum(c, Mushroom.RingNumber.values()));
		COLUMNS.put("ring-type", c -> getEnum(c, Mushroom.RingType.values()));
		COLUMNS.put("spore-print-color", c -> getEnum(c, Mushroom.Color.values()));
		COLUMNS.put("population", c -> getEnum(c, Mushroom.Population.values()));
		COLUMNS.put("habitat", c -> getEnum(c, Mushroom.Habitat.values()));
	}
	private final File file;

	public MushroomParser(File file) {
		this.file = file;
	}

	public List<Mushroom> parseMushrooms() throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
		List<Mushroom> mushrooms = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Constructor<Mushroom> constructor = (Constructor<Mushroom>) Mushroom.class.getConstructors()[0];
		try(Scanner sc = new Scanner(file)) {
			String[] header = sc.nextLine().split(",");
			if(constructor.getParameterCount() != header.length) {
				throw new IllegalArgumentException("File need " + constructor.getParameterCount() + " headers");
			}
			while(sc.hasNextLine()) {
				String[] chars = sc.nextLine().split(",");
				Object[] params = new Object[header.length];
				for(int i = 0; i < params.length; i++) {
					CharColumnParser column = COLUMNS.get(header[i]);
					if(column == null) {
						throw new IllegalArgumentException("Unexpected header " + header[i]);
					}
					params[i] = column.getValue(chars[i].charAt(0));
				}
				mushrooms.add(constructor.newInstance(params));
			}
		}
		return mushrooms;
	}

	private static <E extends Mushroom.CharEnum> E getEnum(char c, E[] values) {
		for(E value : values) {
			if(value.getChar() == c) {
				return value;
			}
		}
		throw new IllegalArgumentException("Unexpected value " + c + " for " + values[0].getClass());
	}

	@FunctionalInterface
	private interface CharColumnParser {
		Object getValue(char c);
	}
}
