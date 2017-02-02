package nl.hro.cmibod023t.test.adult;

import nl.hro.cmibod023t.classification.Testable;

public class Person implements Testable<String> {
	private final int age;
	private final String workClass;
	private final int fnlwgt;
	private final String education;
	private final int educationNum;
	private final String maritalStatus;
	private final String occupation;
	private final String relationship;
	private final String race;
	private final String sex;
	private final int capitalGain;
	private final int capitalLoss;
	private final int hoursPerWeek;
	private final String nativeCountry;
	private final String income;

	public Person(int age, String workClass, int fnlwgt, String education, int educationNum, String maritalStatus, String occupation, String relationship, String race, String sex, int capitalGain, int capitalLoss, int hoursPerWeek, String nativeCountry, String income) {
		this.age = age;
		this.workClass = workClass;
		this.fnlwgt = fnlwgt;
		this.education = education;
		this.educationNum = educationNum;
		this.maritalStatus = maritalStatus;
		this.occupation = occupation;
		this.relationship = relationship;
		this.race = race;
		this.sex = sex;
		this.capitalGain = capitalGain;
		this.capitalLoss = capitalLoss;
		this.hoursPerWeek = hoursPerWeek;
		this.nativeCountry = nativeCountry;
		this.income = income;
	}

	public int getAge() {
		return age;
	}

	public String getWorkClass() {
		return workClass;
	}

	public int getFnlwgt() {
		return fnlwgt;
	}

	public String getEducation() {
		return education;
	}

	public int getEducationNum() {
		return educationNum;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getRelationship() {
		return relationship;
	}

	public String getRace() {
		return race;
	}

	public String getSex() {
		return sex;
	}

	public int getCapitalGain() {
		return capitalGain;
	}

	public int getCapitalLoss() {
		return capitalLoss;
	}

	public int getHoursPerWeek() {
		return hoursPerWeek;
	}

	public String getNativeCountry() {
		return nativeCountry;
	}

	public String getIncome() {
		return income;
	}

	@Override
	public Object[] getFeatures() {
		return new Object[] {
				getWorkClass(), getEducation(), getMaritalStatus(), getOccupation(), getRelationship(), getRace(), getSex(), getNativeCountry()
		};
	}

	@Override
	public String getTargetClass() {
		return income;
	}
}
