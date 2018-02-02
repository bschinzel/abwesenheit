package de.schinzel.kalender.rest.v1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.Cache;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import de.schinzel.kalender.metrics.Metrics;
import de.schinzel.kalender.model.Datum;
import de.schinzel.kalender.model.Kalender;

@Path("/v1")
public class KalenderEndpoint {
	
	private static final Logger LOGGER = Logger.getLogger(KalenderEndpoint.class); 
	private final Meter requestsTotal = Metrics.REGISTRY.meter("GET /kalender/v1/*");
	private final Timer requestsGetTag = Metrics.REGISTRY.timer(MetricRegistry.name(KalenderEndpoint.class, "GET /kalender/v1/{jahr}/{monat}/{tag}"));
	private final Timer requestsGetMonat = Metrics.REGISTRY.timer(MetricRegistry.name(KalenderEndpoint.class, "GET /kalender/v1/{jahr}/{monat}"));
	private final Timer requestsGetJahr = Metrics.REGISTRY.timer(MetricRegistry.name(KalenderEndpoint.class, "GET /kalender/v1/{jahr}"));
	
	@GET
	@Path("/{jahr}/{monat}/{tag}")
	@Produces(MediaType.APPLICATION_JSON)
	@Cache(maxAge = 3600, sMaxAge = 3600, isPrivate = false, noStore = false, mustRevalidate = false, proxyRevalidate = false)
	public Datum tag(@PathParam("jahr") int jahr, @PathParam("monat") int monat, @PathParam("tag") int tag) {
		LOGGER.tracef("GET /%d/%d/%d", jahr, monat, tag);
		requestsTotal.mark();
		final Timer.Context timercontext = requestsGetTag.time();
				
		try {
			Datum datum = Kalender.DATEN_MAP.get(jahr + "-" + this.zerofill(monat, 2) + "-" + this.zerofill(tag, 2));
			return datum;
		} catch (Exception e) {
			throw e;
		} finally {
			timercontext.stop();
		}
	}
	
	@GET
	@Path("/{jahr}/{monat}")
	@Produces(MediaType.APPLICATION_JSON)
	@Cache(maxAge = 3600, sMaxAge = 3600, isPrivate = false, noStore = false, mustRevalidate = false, proxyRevalidate = false)
	public List<Datum> monat(@PathParam("jahr") int jahr, @PathParam("monat") int monat) {
		LOGGER.tracef("GET /%d/%d", jahr, monat);
		requestsTotal.mark();
		final Timer.Context timercontext = requestsGetMonat.time();
		
		try {
			ArrayList<Datum> daten = new ArrayList<>(31);
		
			LocalDate d = LocalDate.of(jahr, monat, 1);
			
			while (d.getMonthValue() != monat + 1 
					&& !(d.getMonthValue() == 1 && monat + 1 == 13)) {
				daten.add(Kalender.DATEN_MAP.get(d.toString()));
				d = d.plusDays(1L);
			}
			return daten;
		} catch (Exception e) {
			throw e;
		} finally {
			timercontext.stop();
		}
		
	}
	
	@GET
	@Path("/{jahr}")
	@Produces(MediaType.APPLICATION_JSON)
	@Cache(maxAge = 3600, sMaxAge = 3600, isPrivate = false, noStore = false, mustRevalidate = false, proxyRevalidate = false)
	public List<Datum> jahr(@PathParam("jahr") int jahr) {
		LOGGER.tracef("GET /%d", jahr);
		requestsTotal.mark();
		final Timer.Context timercontext = requestsGetJahr.time();
		
		try {
			ArrayList<Datum> daten = new ArrayList<>(366);
			LocalDate d = LocalDate.of(jahr, 1, 1);
			
			while (d.getYear() != jahr + 1) {
				daten.add(Kalender.DATEN_MAP.get(d.toString()));
				d = d.plusDays(1L);
			}
			return daten;
		} catch (Exception e) {
			throw e;
		} finally {
			timercontext.stop();
		}
	}

	private String zerofill(int val, int laenge) {
		String monatStr = String.valueOf(val);
		
		while (monatStr.length() < laenge) {
			monatStr = "0" + monatStr;
		}
		
		return monatStr;
	}
}
