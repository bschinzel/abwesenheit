package de.schinzel.kalender.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import org.jboss.logging.Logger;

public class Kalender {
	private static final Logger LOGGER = Logger.getLogger(Kalender.class);
	public static final LinkedList<Datum> DATEN = new LinkedList<>();
	public static final HashMap<String, Datum> DATEN_MAP = new HashMap<>();
	private static final LocalDate MIN_DATUM = LocalDate.of(2000, 1, 1);
	private static final LocalDate MAX_DATUM = LocalDate.of(2030, 12, 31);
	private static final Bundesland BUNDESLAND = Bundesland.valueOf(System.getenv("BUNDESLAND"));
	
	static {
		LocalDate localDate = MIN_DATUM;
		
		int jahr = localDate.getYear() - 1;
		int kalenderwoche = 0;
		
		while (!localDate.equals(MAX_DATUM)) {
			
			if (jahr != localDate.getYear()) {
				jahr = localDate.getYear();
				kalenderwoche = 0;
			}
			
			if (kalenderwoche == 0 && istInErsterKalenderwoche(localDate)) {
				kalenderwoche = 1;
			} else if (kalenderwoche > 0 && localDate.getDayOfWeek().getValue() == 1) {
				kalenderwoche++;
			}
			
			final Datum datum = new Datum(localDate, BUNDESLAND, kalenderwoche);
			DATEN.add(datum);
			DATEN_MAP.put(localDate.toString(), datum);
			
			localDate = localDate.plusDays(1L);
		}
		
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("Kalender:");
			for (Datum d : DATEN) {
				LOGGER.trace(d.toString());
			}
		}
		
		LOGGER.debug("Kalender für Bundesland '" + BUNDESLAND.getName() + "' geladen");
	}

	private static boolean istInErsterKalenderwoche(LocalDate localDate) {
		
		LocalDate d = localDate;
		
		while (d.getDayOfWeek().getValue() < 7) {
			
			if (d.getMonth().getValue() == 1 && d.getDayOfMonth() == 4) {
				return true;
			}
			
			d = d.plusDays(1L);
		}
		
		return false;
	}
}
