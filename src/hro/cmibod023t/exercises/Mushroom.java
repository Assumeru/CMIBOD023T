package hro.cmibod023t.exercises;

import hro.cmibod023t.classification.DecisionTree;
import hro.cmibod023t.classification.NaiveClassifier;
import hro.cmibod023t.classification.Result;

public class Mushroom {
	private final Class type;
	private final CapShape capShape;
	private final Surface capSurface;
	private final Color capColor;
	private final boolean bruised;
	private final Odor odor;
	private final GillAttachment gillAttachment;
	private final GillSpacing gillSpacing;
	private final GillSize gillSize;
	private final Color gillColor;
	private final StalkShape stalkShape;
	private final StalkRoot stalkRoot;
	private final Surface stalkSurfaceAboveRing;
	private final Surface stalkSurfaceBelowRing;
	private final Color stalkColorAboveRing;
	private final Color stalkColorBelowRing;
	private final VeilType veilType;
	private final Color veilColor;
	private final RingNumber ringNumber;
	private final RingType ringType;
	private final Color sporePrintColor;
	private final Population population;
	private final Habitat habitat;

	public Mushroom(Class type, CapShape capShape, Surface capSurface, Color capColor, boolean bruised, Odor odor, GillAttachment gillAttachment, GillSpacing gillSpacing, GillSize gillSize, Color gillColor, StalkShape stalkShape, StalkRoot stalkRoot, Surface stalkSurfaceAboveRing, Surface stalkSurfaceBelowRing, Color stalkColorAboveRing, Color stalkColorBelowRing, VeilType veilType, Color veilColor, RingNumber ringNumber, RingType ringType, Color sporePrintColor, Population population, Habitat habitat) {
		this.type = type;
		this.capShape = capShape;
		this.capSurface = capSurface;
		this.capColor = capColor;
		this.bruised = bruised;
		this.odor = odor;
		this.gillAttachment = gillAttachment;
		this.gillSpacing = gillSpacing;
		this.gillSize = gillSize;
		this.gillColor = gillColor;
		this.stalkShape = stalkShape;
		this.stalkRoot = stalkRoot;
		this.stalkSurfaceAboveRing = stalkSurfaceAboveRing;
		this.stalkSurfaceBelowRing = stalkSurfaceBelowRing;
		this.stalkColorAboveRing = stalkColorAboveRing;
		this.stalkColorBelowRing = stalkColorBelowRing;
		this.veilType = veilType;
		this.veilColor = veilColor;
		this.ringNumber = ringNumber;
		this.ringType = ringType;
		this.sporePrintColor = sporePrintColor;
		this.population = population;
		this.habitat = habitat;
	}

	public Class getType() {
		return type;
	}

	public CapShape getCapShape() {
		return capShape;
	}

	public Surface getCapSurface() {
		return capSurface;
	}

	public Color getCapColor() {
		return capColor;
	}

	public boolean isBruised() {
		return bruised;
	}

	public Odor getOdor() {
		return odor;
	}

	public GillAttachment getGillAttachment() {
		return gillAttachment;
	}

	public GillSpacing getGillSpacing() {
		return gillSpacing;
	}

	public GillSize getGillSize() {
		return gillSize;
	}

	public Color getGillColor() {
		return gillColor;
	}

	public StalkShape getStalkShape() {
		return stalkShape;
	}

	public StalkRoot getStalkRoot() {
		return stalkRoot;
	}

	public Surface getStalkSurfaceAboveRing() {
		return stalkSurfaceAboveRing;
	}

	public Surface getStalkSurfaceBelowRing() {
		return stalkSurfaceBelowRing;
	}

	public Color getStalkColorAboveRing() {
		return stalkColorAboveRing;
	}

	public Color getStalkColorBelowRing() {
		return stalkColorBelowRing;
	}

	public VeilType getVeilType() {
		return veilType;
	}

	public Color getVeilColor() {
		return veilColor;
	}

	public RingNumber getRingNumber() {
		return ringNumber;
	}

	public RingType getRingType() {
		return ringType;
	}

	public Color getSporePrintColor() {
		return sporePrintColor;
	}

	public Population getPopulation() {
		return population;
	}

	public Habitat getHabitat() {
		return habitat;
	}

	public interface CharEnum {
		char getChar();
	}

	public enum Class implements CharEnum {
		POISONOUS('p'), EDIBLE('e');
		private final char character;

		private Class(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum CapShape implements CharEnum {
		BELL('b'), CONICAL('c'), CONVEX('x'), FLAT('f'), KNOBBED('k'), SUNKEN('s');
		private final char character;

		private CapShape(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum Surface implements CharEnum {
		FIBROUS('f'), GROOVES('g'), SCALY('y'), SMOOTH('s'), SILKY('k');
		private final char character;

		private Surface(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum Color implements CharEnum {
		BLACK('k'), BROWN('n'), BUFF('b'), CHOCOLATE('h'), GRAY('g'), GREEN('r'), ORANGE('o'), PINK('p'), PURPLE('u'), RED('e'), WHITE('w'), YELLOW('y'), CINNAMON('c');
		private final char character;

		private Color(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum Odor implements CharEnum {
		ALMOND('a'), ANISE('l'), CREOSOTE('c'), FISHY('y'), FOUL('f'), MUSTY('m'), NONE('n'), PUNGENT('p'), SPICY('s');
		private final char character;

		private Odor(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum GillAttachment implements CharEnum {
		ATTACHED('a'), DESCENDING('l'), FREE('f'), NOTCHED('n');
		private final char character;

		private GillAttachment(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum GillSpacing implements CharEnum {
		CLOSE('c'), CROWDED('w'), DISTANT('d');
		private final char character;

		private GillSpacing(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum GillSize implements CharEnum {
		BROAD('b'), NARROW('n');
		private final char character;

		private GillSize(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum StalkShape implements CharEnum {
		ENLARGING('e'), TAPERING('t');
		private final char character;

		private StalkShape(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum StalkRoot implements CharEnum {
		BULBOUS('b'), CLUB('c'), CUP('u'), EQUAL('e'), RHIZOMORPHS('z'), ROOTED('r'), MISSING('?');
		private final char character;

		private StalkRoot(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum VeilType implements CharEnum {
		PARTIAL('p'), UNIVERSAL('u');
		private final char character;

		private VeilType(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum RingNumber implements CharEnum {
		NONE('n'), ONE('o'), TWO('t');
		private final char character;

		private RingNumber(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum RingType implements CharEnum {
		COBWEBBY('c'), EVANESCENT('e'), FLARING('f'), LARGE('l'), NONE('n'), PENDANT('p'), SHEATHING('s'), ZONE('z');
		private final char character;

		private RingType(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum Population implements CharEnum {
		ABUNDANT('a'), CLUSTERED('c'), NUMEROUS('n'), SCATTERED('s'), SEVERAL('v'), SOLIDARY('y');
		private final char character;

		private Population(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public enum Habitat implements CharEnum {
		GRASSES('g'), LEAVES('l'), MEADOWS('m'), PATHS('p'), URBAN('u'), WASTE('w'), WOODS('d');
		private final char character;

		private Habitat(char character) {
			this.character = character;
		}

		@Override
		public char getChar() {
			return character;
		}
	}

	public static Classifier createNaiveBayesianClassifier() {
		return new NBClassifier();
	}

	public static Classifier createDecisionTree() {
		return new TreeClassifier();
	}

	public interface Classifier extends hro.cmibod023t.classification.Classifier<Mushroom.Class> {
		public default Result<Class> test(Mushroom mushroom) {
			return test(getFeatures(mushroom));
		}

		public default Classifier train(Mushroom mushroom) {
			train(mushroom.getType(), getFeatures(mushroom));
			return this;
		}
	}

	private static Object[] getFeatures(Mushroom mushroom) {
		return new Object[] {
				mushroom.getCapShape(), mushroom.getCapSurface(), mushroom.getCapColor(),
				mushroom.isBruised(), mushroom.getOdor(), mushroom.getGillAttachment(),
				mushroom.getGillSpacing(), mushroom.getGillSize(), mushroom.getGillColor(),
				mushroom.getStalkShape(), mushroom.getStalkRoot(), mushroom.getStalkSurfaceAboveRing(),
				mushroom.getStalkSurfaceBelowRing(), mushroom.getStalkColorAboveRing(), mushroom.getStalkColorBelowRing(),
				mushroom.getVeilType(), mushroom.getVeilColor(), mushroom.getRingNumber(),
				mushroom.getRingType(), mushroom.getSporePrintColor(), mushroom.getPopulation(), mushroom.getHabitat()
		};
	}

	public static class NBClassifier extends NaiveClassifier<Mushroom.Class> implements Classifier {
		public NBClassifier() {
			super(CapShape.class, Surface.class, Color.class, boolean.class, Odor.class, GillAttachment.class, GillSpacing.class, GillSize.class, Color.class, StalkShape.class, StalkRoot.class, Surface.class, Surface.class, Color.class, Color.class, VeilType.class, Color.class, RingNumber.class, RingType.class, Color.class, Population.class, Habitat.class);
		}
	}

	public static class TreeClassifier extends DecisionTree<Mushroom.Class> implements Classifier {
		public TreeClassifier() {
			super(CapShape.class, Surface.class, Color.class, boolean.class, Odor.class, GillAttachment.class, GillSpacing.class, GillSize.class, Color.class, StalkShape.class, StalkRoot.class, Surface.class, Surface.class, Color.class, Color.class, VeilType.class, Color.class, RingNumber.class, RingType.class, Color.class, Population.class, Habitat.class);
		}
	}
}
