package de.schinzel.kalender.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Siehe https://de.wikipedia.org/wiki/Gesetzliche_Feiertage_in_Deutschland#.C3.9Cbersicht_aller_gesetzlichen_Feiertage
 */
public class Feiertag {
	private final String gesetzlicheBezeichnung;
	private final String datumBeschreibung;
	private final String url;
	
	@JsonIgnore
	private final boolean[] gueltigkeitBundesland;
	
	@JsonIgnore
	private final boolean[] optionalBundesland;
	private final boolean bundesweit;
	private final String anmerkung;

	public Feiertag(String datum, String gesetzlicheBezeichnung, String url, 
			boolean[] gueltigkeitBundesland, boolean[] optionalBundesland, boolean bundesweit,
			String anmerkung) {
		this.datumBeschreibung = datum;
		this.gesetzlicheBezeichnung = gesetzlicheBezeichnung;
		this.url = url;
		this.gueltigkeitBundesland = gueltigkeitBundesland;
		this.optionalBundesland = optionalBundesland;
		this.bundesweit = bundesweit;
		this.anmerkung = anmerkung;
	}

	public String getGesetzlicheBezeichnung() {
		return gesetzlicheBezeichnung;
	}

	public String getDatumBeschreibung() {
		return datumBeschreibung;
	}

	public String getUrl() {
		return url;
	}
	
	public boolean[] getGueltigkeitBundesland() {
		return gueltigkeitBundesland;
	}
	
	public boolean[] getOptionalBundesland() {
		return optionalBundesland;
	}

	public boolean isBundesweit() {
		return bundesweit;
	}

	public String getAnmerkung() {
		return anmerkung;
	}
}
