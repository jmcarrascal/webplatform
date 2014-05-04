package ar.com.jmc.webplatform.base.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public final class XMLUtils {
	private static Logger log = Logger.getLogger(XMLUtils.class);

	private XMLUtils() {}
	
	private static String dateToString(Date fecha) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reportDate = df.format(fecha);
		return reportDate;
	}

	private static XMLGregorianCalendar getFechaXMLGregorianCalendar(
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

	private static XMLGregorianCalendar getFechaXMLGregorianCalendar(Date date) {
		if(date==null) return null;
		String fecha = dateToString(date);
		return getFechaXMLGregorianCalendar(fecha);
	}
	
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		if (date == null) return null;
		try {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Map<String, String> validXML(URL urlSchema,File urlXSD) throws MalformedURLException, SAXException{
		Map<String, String> responseMap = new HashMap<String, String>();
		Source xmlFile = new StreamSource( urlXSD);
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);			
		Schema schema = schemaFactory.newSchema(urlSchema);
		Validator validator = schema.newValidator();
		try {
		  validator.validate(xmlFile);
		  log.debug(xmlFile.getSystemId() + " valido");
		  responseMap.put("valid","success");
		} catch (SAXException e) {
		  log.error(xmlFile.getSystemId() + " no valido");
		  log.error("Reason: " + e.getLocalizedMessage());
		  responseMap.put("valid","false");
		  responseMap.put("razon",e.getLocalizedMessage());
		} catch (IOException e) {
			log.error(xmlFile.getSystemId() + " no valido");
			log.error("Reason: " + e.getLocalizedMessage());
			responseMap.put("valid","false");
			responseMap.put("razon",e.getLocalizedMessage());
		}
		return responseMap;
	}
}
