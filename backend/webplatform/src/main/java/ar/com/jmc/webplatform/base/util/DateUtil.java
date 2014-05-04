package ar.com.jmc.webplatform.base.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Transient;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

/**
 * @author Juan Manuel Carrascal
 * @version 1.0
 * @created 12-Jan-2010 11:19:57 AM
 */
public class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);

	private static SimpleDateFormat shortSDF = new SimpleDateFormat("dd / MM / yyyy");

	// private static SimpleDateFormat longSDF = new
	// SimpleDateFormat("dd de MMMM de yyyy");
	@Transient
	public static boolean getDaysCompareEntryToNow(Timestamp fecEntrada, long days) {
		boolean out = false;
		Date date = DateUtil.getCurrentDate();
		Timestamp fechaActual = new Timestamp(date.getTime());
		// 84600000 milliseconds in a day
		long oneDay = 1 * 24 * 60 * 60 * 1000;
		long tsEntrada = fecEntrada.getTime();
		long tsActual = fechaActual.getTime();
		long tsResult = tsActual - tsEntrada;
		long day = tsResult / oneDay;
		if (day > days) {
			out = true;
		}
		return out;
	}

	public static Timestamp restaTimestampByDays(Timestamp fecha, Long days) {
		Long oneDay = 1l * 24l * 60l * 60l * 1000l;
		days = oneDay * days;
		Long tsResult = fecha.getTime() - days;
		fecha.setTime(tsResult);
		return fecha;
	}

	public static Timestamp sumaTimestampByDays(Timestamp fecha, Long days) {
		Long oneDay = 1l * 24l * 60l * 60l * 1000l;
		days = oneDay * days;
		Long tsResult = fecha.getTime() + days;
		fecha.setTime(tsResult);
		return fecha;
	}

	public static String difTimeStampToTimeStamp(Timestamp fechaInicial, Timestamp fechaFinal) {
		String result = "";
		long oneDay = 24 * 60 * 60 * 1000;
		long oneHour = 60 * 60 * 1000;
		long oneMinute = 60 * 1000;
		if (fechaFinal == null) {
			Date date = DateUtil.getCurrentDate();
			fechaFinal = new Timestamp(date.getTime());
		}
		Long tsEntrada = fechaInicial.getTime();
		Long tsActual = fechaFinal.getTime();
		long tsResult = tsActual - tsEntrada;
		Timestamp tiempoDif = new Timestamp(tsResult);
		log.debug("Resultado " + tiempoDif);
		/*
		 * Obtengo los dias
		 */
		Long dias = tsResult / oneDay;
		/*
		 * Rescato el resto de la divicion de dias para obtener las horas.
		 */
		Long restoDias = tsResult % oneDay;
		/*
		 * Obtengo las horas en base del resto de los dias.
		 */
		Long horas = restoDias / oneHour;
		/*
		 * Rescato el resto de la divicion de horas para obtener las minutos.
		 */
		Long restoHoras = restoDias % oneHour;
		/*
		 * Obtengo los minutos en base del resto de los Horas.
		 */
		Long minutos = restoHoras / oneMinute;
		/*
		 * log.debug("dias " + dias); log.debug("horas " +
		 * horas); log.debug("minutos " + minutos);
		 */
		result = dias + "d " + horas + "h " + minutos + "m";
		return result;
	}

	public static String getMonthName(int monthNumber) {
		String month = null;
		switch (monthNumber) {
			case 0:
				month = " Enero ";
				break;
			case 1:
				month = " Febrero ";
				break;
			case 2:
				month = " Marzo ";
				break;
			case 3:
				month = " Abril ";
				break;
			case 4:
				month = " Mayo ";
				break;
			case 5:
				month = " Junio ";
				break;
			case 6:
				month = " Julio ";
				break;
			case 7:
				month = " Agosto ";
				break;
			case 8:
				month = " Septiembre ";
				break;
			case 9:
				month = " Octubre ";
				break;
			case 10:
				month = " Noviembre ";
				break;
			case 11:
				month = " Diciembre ";
				break;
		}
		return month;
	}
	
	public static String getFormattedDate (Date date){
		if (date != null){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return (date==null)?" - ":format.format(new Date(date.getTime()));
		}else{
			return null;
		}

	}

	public static String getFormattedDateOld(Date date) {
		int day, month, year;
		Calendar cal = new GregorianCalendar();
		String result = "";
		if (date != null) {
			try {
				cal.setTime(date);
				day = cal.get(Calendar.DATE);
				month = cal.get(Calendar.MONTH);
				year = cal.get(Calendar.YEAR);
				result = String.valueOf(day) + " de" + getMonthName(month) + "de " + String.valueOf(year);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Timestamp composeDate(String date) {
		Calendar cal = new GregorianCalendar();
		int year = Integer.parseInt(date.substring(date.lastIndexOf("/") + 1, date.length()));
		int month = Integer.parseInt(date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"))) - 1;
		int day = Integer.parseInt(date.substring(0, date.indexOf("/")));
		cal.set(year, month, day);
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp composeDateGuion(String date) {
		Calendar cal = new GregorianCalendar();
		int year = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1, date.length()));
		int month = Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"))) - 1;
		int day = Integer.parseInt(date.substring(0, date.indexOf("-")));
		cal.set(year, month, day);
		return new Timestamp(cal.getTime().getTime());
	}

	public static String getShortFormattedDate(Date date) {
		/*
		 * int day,month,year; Calendar cal = new GregorianCalendar(); String
		 * result = ""; if(date != null){ cal.setTime(date); day =
		 * cal.get(Calendar.DATE); month = cal.get(Calendar.MONTH); year =
		 * cal.get(Calendar.YEAR); result = String.valueOf(day) + "_" +
		 * getMonthName(month) + "_" + String.valueOf(year); } return result;
		 */
		if (date != null) {
			return shortSDF.format(date);
		} else {
			return "";
		}
	}
	
	/**
	 * Retorna la fecha actual, la cual se recupera desde la base de datos
	 * @return la fecha actual
	 */
	// TODO1: este metodo contiene sentencias propietarias del motor de BD
	public static Date getCurrentDate() {
		//return (Date)DBConnection.executeSQLQuery(Constants.INI_PARAM_BD_URL, Constants.INI_PARAM_BD_USER, Constants.INI_PARAM_BD_PASS, "SELECT sysdate FROM dual");
		return new Date(System.currentTimeMillis());
	}
	
	
	/**
	 * @return Copia de la fecha recibida, con todos los campos menores a dia con valor cero 
	 */
	public static Date truncate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * @return Copia de la fecha recibida, con el ultimo milisegundo del dia
	 * (igual a dia siguiente menos un milisegundo) 
	 */
	public static Date setLastMillisecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
		cal.add(Calendar.MILLISECOND, -1);
		return cal.getTime();
	}
	
	
	public static String dateToString(Date fecha) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reportDate = df.format(fecha);
		return reportDate;
	}

	public static XMLGregorianCalendar getFechaXMLGregorianCalendar(
			String fecha) {

		Date dob = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dob = df.parse(fecha);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dob);
		XMLGregorianCalendar xmlDate = null;
		try {
			xmlDate = DatatypeFactory.newInstance()
					.newXMLGregorianCalendarDate(cal.get(Calendar.YEAR),
							cal.get(Calendar.MONTH) + 1,
							cal.get(Calendar.DAY_OF_MONTH),
							DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}
		return xmlDate;
	}

	public static XMLGregorianCalendar getFechaXMLGregorianCalendar(Date date) {
		String fecha = dateToString(date);
		return getFechaXMLGregorianCalendar(fecha);
	}
}
