package de.schinzel.kalender.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Datum {
	private static final Logger LOGGER = Logger.getLogger(Datum.class);
	private static final HashMap<String, LocalDate> OSTERSONNTAGE = new HashMap<String, LocalDate>();
	
	static {
		LOGGER.trace("Ostersonntage:");
		
		for (int jahr = 2000; jahr <= 2050; jahr++) {
			// https://de.wikipedia.org/wiki/Spencers_Osterformel
			int a = jahr % 19;
			int b = jahr / 100;
			int c = jahr % 100;
			int d = b / 4;
			int e = b % 4;
			int f = (b + 8) / 25;
			int g = (b - f + 1) / 3;
			int h = (19 * a + b - d - g + 15) % 30;
			int i = c / 4;
			int k = c % 4;
			int l = (32 + 2 * e + 2 * i - h - k) % 7;
			int m = (a + 11 * h + 22 * l) / 451;
			int n = (h + l - 7 * m + 114) / 31;
			int p = (h + l - 7 * m + 114) % 31;
			
			final LocalDate sonntag = LocalDate.of(jahr, n, p + 1);
			OSTERSONNTAGE.put(sonntag.toString(), sonntag);
			LOGGER.trace(sonntag.toString() + " " + sonntag.getDayOfWeek().toString());
		}
	}
	
	private static final Feiertag NEUJAHR = new Feiertag("1. Januar", "Neujahr(stag)", "https://de.wikipedia.org/wiki/Neujahr", 
			//             BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag HEILIGE_DREI_KOENIGE = new Feiertag("6. Januar", "Heilige Drei Könige (Epiphanias), Erscheinungsfest", "https://de.wikipedia.org/wiki/Heilige_Drei_K%C3%B6nige", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  false, false, false, false, false, false, false, false, false, false, false, true,  false, false},
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			null);
	
	private static final Feiertag GRUENDONNERSTAG = new Feiertag("Donnerstag vor Ostersonntag", "Gründonnerstag", "https://de.wikipedia.org/wiki/Gr%C3%BCndonnerstag", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			new boolean[] {true,  false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			"Baden-Württemberg: Gemäß § 4 Abs. 3 des Feiertagsgesetzes von Baden-Württemberg haben Schüler am Gründonnerstag und am Reformationstag schulfrei. In der Regel legt das Kultusministerium die Ferientermine so fest, dass diese beiden Tage in die Osterferien bzw. in die Herbstferien fallen.");
	
	private static final Feiertag KARFREITAG = new Feiertag("Freitag vor Ostersonntag", "Karfreitag", "https://de.wikipedia.org/wiki/Karfreitag", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag OSTERSONNTAG = new Feiertag("Das Osterdatum ist das Datum des Osterfestes im Kirchenjahr", "Ostersonntag", "https://de.wikipedia.org/wiki/Ostersonntag", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false},
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			null);
	
	private static final Feiertag OSTERMONTAG = new Feiertag("Montag nach Ostersonntag", "Ostermontag", "https://de.wikipedia.org/wiki/Ostermontag", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag TAG_DER_ARBEIT = new Feiertag("1. Mai", "Erster Mai, Tag der Arbeit", "https://de.wikipedia.org/wiki/Erster_Mai", 
			//		       BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag CHRISTI_HIMMELFAHRT = new Feiertag("Ostersonntag + 39 Tage", "(Christi-)Himmelfahrt(stag)", "https://de.wikipedia.org/wiki/Christi_Himmelfahrt", 
			//			   BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag PFINGSTSONNTAG = new Feiertag("Ostersonntag + 49 Tage", "Pfingstsonntag", "https://de.wikipedia.org/wiki/Pfingsten", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {false, false, false, true,  false, false, true,  false, false, false, false, false, false, false, false, false},
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			null);
	
	private static final Feiertag PFINGSTMONTAG = new Feiertag("Montag nach Pfingstsonntag", "Pfingstmontag", "https://de.wikipedia.org/wiki/Pfingstmontag", 
			//			   BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag FRONLEICHNAHM = new Feiertag("Ostersonntag + 60 Tage", "Fronleichnam(stag)", "https://de.wikipedia.org/wiki/Fronleichnam", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  false, false, false, false, true,  false, false, true,  true,  true,  false, false, false, false},
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, true,  false, false, true },
			false,
			"Sachsen: Fronleichnam ist gesetzlicher Feiertag in folgenden katholisch geprägten Gemeinden des sorbischen Siedlungsgebietes im Landkreis Bautzen: "
			+ "Bautzen (nur in den Ortsteilen Bolbritz und Salzenforst), Crostwitz, Göda (nur im Ortsteil Prischwitz), Großdubrau (nur im Ortsteil Sdier), Hoyerswerda (nur im Ortsteil Dörgenhausen), Königswartha (nicht im Ortsteil Wartha), Nebelschütz, Neschwitz "
			+ "(nur in den Ortsteilen Neschwitz und Saritsch), Panschwitz-Kuckau, Puschwitz, Räckelwitz, Radibor, Ralbitz-Rosenthal und Wittichenau. Entscheidend ist dabei der Arbeitsort, nicht der Wohnort eines Arbeitnehmers."
			+ " Die gesetzliche Grundlage für diese durch die Fronleichnamsverordnung festgelegte Regelung ergibt sich aus § 1 Abs. 1 des Sächsischen Feiertagsgesetzes."
			+ "In den restlichen Gemeinden Sachsens ist Fronleichnam als religiöser Feiertag durch § 3 Abs. 1 des Sächsischen Feiertagsgesetzes geschützt."
			+ "Thüringen: Fronleichnam ist gesetzlicher Feiertag im gesamten Landkreis Eichsfeld (79 Gemeinden am 31. Dezember 2013, Auflistung siehe dort) sowie in folgenden Gemeinden des Unstrut-Hainich-Kreises und des Wartburgkreises:"
			+ "Anrode (nur in den Ortsteilen Bickenriede und Zella), Brunnhartshausen (nur in den Ortsteilen Föhlritz und Steinberg), Buttlar, Dünwald (nur in den Ortsteilen Beberstedt und Hüpstedt), Geisa, Rodeberg (nur im Ortsteil Struth), Schleid, Südeichsfeld und Zella/Rhön."
			+ " Die gesetzliche Grundlage für diese Regelung ergibt sich aus § 2 Abs. 2 und § 10 Abs. 1 des Thüringer Feier- und Gedenktagsgesetzes."
 			+ "In den restlichen Gemeinden Thüringens ist Fronleichnam als religiöser Feiertag durch § 3 Abs. 1 Nr. 2 des Thüringer Feier- und Gedenktagsgesetzes geschützt.");
	
	private static final Feiertag AUGSBURGER = new Feiertag("8. August", "Augsburger Hohes Friedensfest", "https://de.wikipedia.org/wiki/Augsburger_Hohes_Friedensfest", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			new boolean[] {false, true,  false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			"Bayern: Das Augsburger Friedensfest ist nur im Stadtgebiet Augsburg (nicht jedoch im angrenzenden Umland) gesetzlicher Feiertag (Art. 1 Abs. 2 Bayerisches Gesetz über den Schutz der Sonn- und Feiertage).");
	
	private static final Feiertag MARIAE_HIMMELFAHRT = new Feiertag("15. August", "Mariä Himmelfahrt(stag)", "https://de.wikipedia.org/wiki/Mari%C3%A4_Aufnahme_in_den_Himmel", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, true,  false, false, false, false},
			new boolean[] {false, true,  false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			"Bayern: Mariä Himmelfahrt ist in Bayern in den derzeit 1704 Gemeinden mit überwiegend katholischer Bevölkerung gesetzlicher Feiertag, in den restlichen 352 Gemeinden nicht. „Überwiegend“ bezieht sich dabei nicht auf die Gesamtbevölkerung, sondern auf den Vergleich zwischen evangelischem und katholischem Bevölkerungsanteil. Gemäß Art. 1 Abs. 3 des Bayerischen Feiertagsgesetzes ist es Aufgabe des Bayerischen Landesamtes für Statistik und Datenverarbeitung, festzustellen, in welchen Gemeinden Mariä Himmelfahrt gesetzlicher Feiertag ist. Die aktuelle, seit 2014 gültige Festlegung beruht auf dem Ergebnis der letzten in der Bundesrepublik Deutschland durchgeführten Volkszählung vom 9. Mai 2011, die bis 2013 gültige Regelung auf dem Ergebnis der Volkszählung vom 25. Mai 1987. Gemäß Art. 4 Abs. 3 des Bayerischen Feiertagsgesetzes entfällt im gesamten Bundesland zu Mariä Himmelfahrt an Schulen aller Gattungen der Unterricht. Diese Festlegung gilt ausdrücklich auch in den Teilen Bayerns, in denen dieser Tag kein gesetzlicher Feiertag ist. Üblicherweise liegt der Tag jedoch ohnehin in den Schulferien. Das Bayerische Landesamt für Statistik und Datenverarbeitung stellt eine Übersichtskarte aller Gemeinden, in denen Mariä Himmelfahrt ein Feiertag ist, bereit.");
	
	private static final Feiertag DEUTSCHE_EINHEIT = new Feiertag("3. Oktober", "Tag der Deutschen Einheit", "https://de.wikipedia.org/wiki/Tag_der_Deutschen_Einheit", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag REFORMATIONSTAG = new Feiertag("31. Oktober", "Reformationstag/-fest", "https://de.wikipedia.org/wiki/Reformationstag", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {false, false, false, true,  false, false, false, true,  false, false, false, false, true,  true,  false, true },
			new boolean[] {true,  false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			"Baden-Württemberg: Gemäß § 4 Abs. 3 des Feiertagsgesetzes von Baden-Württemberg haben Schüler am Gründonnerstag und am Reformationstag schulfrei. In der Regel legt das Kultusministerium die Ferientermine so fest, dass diese beiden Tage in die Osterferien bzw. in die Herbstferien fallen.");
	
	private static final Feiertag ALLERHEILIGEN = new Feiertag("1. November", "Allerheiligen(tag)", "https://de.wikipedia.org/wiki/Allerheiligen", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  false, false, false, false, false, false, false, true,  true,  true,  false, false, false, false},
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			null);
	
	private static final Feiertag BUSS_BETTAG = new Feiertag("Mittwoch vor dem 23. November", "Buß- und Bettag", "https://de.wikipedia.org/wiki/Bu%C3%9F-_und_Bettag", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, true,  false, false, false},
			new boolean[] {false, true,  false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			false,
			"Bayern: Gemäß Art. 4 Nr. 3 des Bayerischen Feiertagsgesetzes entfällt im gesamten Bundesland am Buß- und Bettag an allen Schulen der Unterricht.");
	
	private static final Feiertag WEIHNACHTS_1 = new Feiertag("25. Dezember", "1./Erster Weihnachts(feier)tag", "https://de.wikipedia.org/wiki/Weihnachten", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	private static final Feiertag WEIHNACHTS_2 = new Feiertag("26. Dezember", "2./Zweiter Weihnachts(feier)tag", "https://de.wikipedia.org/wiki/Weihnachten", 
			//	           BW     BY     BE     BB     HB     HH     HE     MV     NI     NW     RP     SL     SN     ST     SH     TH
			new boolean[] {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true },
			new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
			true,
			null);
	
	@JsonIgnore
	private final LocalDate localDate;
	
	private final int jahr;
	private final int monat;
	private final int tag;
	private final int kalenderwoche;
	private final String wochentag;
	
	private Feiertag feiertag;
	
	public Datum(LocalDate localDate, Bundesland bundesland, int kalenderwoche) {
		this.kalenderwoche = kalenderwoche;
		this.wochentag = localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.GERMAN);
		this.localDate = localDate;
		this.jahr = localDate.getYear();
		this.monat = localDate.getMonthValue();
		this.tag = localDate.getDayOfMonth();
		this.verknuepfeMitFeiertag(bundesland);
	}

	public Feiertag getFeiertag() {
		return feiertag;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void verknuepfeMitFeiertag(Bundesland bundesland) {
		
		// Nicht bewegliche Feiertage
		if (istTagDesMonats(1, 1) && istFeiertagInBundesland(NEUJAHR, bundesland)) {
			this.feiertag = NEUJAHR;
		} else if (istTagDesMonats(6, 1) && istFeiertagInBundesland(HEILIGE_DREI_KOENIGE, bundesland)) {
			this.feiertag = HEILIGE_DREI_KOENIGE;
		} else if (istTagDesMonats(1, 5) && istFeiertagInBundesland(TAG_DER_ARBEIT, bundesland)) {
			this.feiertag = TAG_DER_ARBEIT;
		} else if (istTagDesMonats(8, 8) && istFeiertagInBundesland(AUGSBURGER, bundesland)) {
			this.feiertag = AUGSBURGER;
		} else if (istTagDesMonats(15, 8) && istFeiertagInBundesland(MARIAE_HIMMELFAHRT, bundesland)) {
			this.feiertag = MARIAE_HIMMELFAHRT;
		} else if (istTagDesMonats(3, 10) && istFeiertagInBundesland(DEUTSCHE_EINHEIT, bundesland)) {
			this.feiertag = DEUTSCHE_EINHEIT;
		} else if (istTagDesMonats(31, 10) && istFeiertagInBundesland(REFORMATIONSTAG, bundesland)) {
			this.feiertag = REFORMATIONSTAG;
		} else if (istTagDesMonats(1, 11) && istFeiertagInBundesland(ALLERHEILIGEN, bundesland)) {
			this.feiertag = ALLERHEILIGEN;
		} else if (istTagDesMonats(25, 12) && istFeiertagInBundesland(WEIHNACHTS_1, bundesland)) {
			this.feiertag = WEIHNACHTS_1;
		} else if (istTagDesMonats(26, 12) && istFeiertagInBundesland(WEIHNACHTS_2, bundesland)) {
			this.feiertag = WEIHNACHTS_2;
			
		// Bewegliche Feiertage mit Referenz auf Ostersonntag
		} else if (OSTERSONNTAGE.containsKey(this.localDate.toString()) 
				&& istFeiertagInBundesland(OSTERSONNTAG, bundesland)) {
			this.feiertag = OSTERSONNTAG;
		} else if (OSTERSONNTAGE.containsKey(this.localDate.minusDays(1L).toString()) 
				&& istFeiertagInBundesland(OSTERMONTAG, bundesland)) {
			this.feiertag = OSTERMONTAG;
		} else if (OSTERSONNTAGE.containsKey(this.localDate.minusDays(39L).toString()) 
				&& istFeiertagInBundesland(CHRISTI_HIMMELFAHRT, bundesland)) {
			this.feiertag = CHRISTI_HIMMELFAHRT;
		} else if (OSTERSONNTAGE.containsKey(this.localDate.minusDays(49L).toString()) 
				&& istFeiertagInBundesland(PFINGSTSONNTAG, bundesland)) {
			this.feiertag = PFINGSTSONNTAG;
		} else if (OSTERSONNTAGE.containsKey(this.localDate.minusDays(50L).toString()) 
				&& istFeiertagInBundesland(PFINGSTMONTAG, bundesland)) {
			this.feiertag = PFINGSTMONTAG;
		} else if (OSTERSONNTAGE.containsKey(this.localDate.minusDays(60L).toString()) 
				&& istFeiertagInBundesland(FRONLEICHNAHM, bundesland)) {
			this.feiertag = FRONLEICHNAHM;
		} else if (OSTERSONNTAGE.containsKey(this.localDate.plusDays(2L).toString()) 
				&& istFeiertagInBundesland(KARFREITAG, bundesland)) {
			this.feiertag = KARFREITAG;
		} else if (OSTERSONNTAGE.containsKey(this.localDate.plusDays(3L).toString()) 
				&& istFeiertagInBundesland(GRUENDONNERSTAG, bundesland)) {
			this.feiertag = GRUENDONNERSTAG;
		
		// Sonstige bewegliche Feiertage
		} else if (this.localDate.getMonthValue() == 11 
				&& this.localDate.getDayOfWeek() == DayOfWeek.WEDNESDAY 
				&& istFeiertagInBundesland(BUSS_BETTAG, bundesland)) {
			
			final LocalDate nov24 = LocalDate.of(this.localDate.getYear(), 11, 24);
			final LocalDate nov16 = LocalDate.of(this.localDate.getYear(), 11, 16);
			
			// 17, 18, 19, 20, 21, 22, 23 November sind mögliche Mittwochs
			// Mi  Do  Fr  Sa  So  Mo  Di
			//     Mi  Do  Fr  Sa  So  Mo
			//         Mi  Do  Fr  Sa  So
			//             Mi  Do  Fr  Sa
			//                 Mi  Do  Fr
			//                     Mi  Do   
			//                         Mi 
			if (this.localDate.isAfter(nov16) && this.localDate.isBefore(nov24)) {
				this.feiertag = BUSS_BETTAG;
			}
		}
	}

	private boolean istFeiertagInBundesland(Feiertag feiertag, Bundesland bundesland) {
		return feiertag.getGueltigkeitBundesland()[bundesland.ordinal()] 
		    || feiertag.getOptionalBundesland()[bundesland.ordinal()];
	}

	private boolean istTagDesMonats(final int tag, final int monat) {
		return this.localDate.getDayOfMonth() == tag && this.localDate.getMonthValue() == monat;
	}
	
	@Override
	public String toString() {
		if (this.feiertag == null) {
			return this.localDate.toString() + " " + this.localDate.getDayOfWeek().toString();
		} else {
			return this.localDate.toString() + " " + this.localDate.getDayOfWeek().toString() 
					+ " (Feiertag: '" + this.feiertag.getGesetzlicheBezeichnung() + "')";			
		}
	}
	
	public int getJahr() {
		return jahr;
	}

	public int getMonat() {
		return monat;
	}

	public int getTag() {
		return tag;
	}

	public String getWochentag() {
		return wochentag;
	}

	public int getKalenderwoche() {
		return kalenderwoche;
	}
}
