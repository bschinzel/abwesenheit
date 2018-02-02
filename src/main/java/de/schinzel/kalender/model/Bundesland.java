package de.schinzel.kalender.model;

public enum Bundesland {
	BW("Baden-Württemberg"),
	BY("Bayern"),
	BE("Berlin"),
	BB("Brandenburg"),
	HB("Freie Hansestadt Bremen"),
	HH("Hamburg"),
	HE("Hessen"),
	MV("Mecklenburg-Vorpommern"),
	NI("Niedersachsen"),
	NW("Nordrhein-Westfalen"),
	RP("Rheinland-Pfalz"),
	SL("Saarland"),
	SN("Sachsen"),
	ST("Sachsen-Anhalt"),
	SH("Schleswig-Holstein"),
	TH("Thüringen");
	
	private final String name;

	private Bundesland(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
