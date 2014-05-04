package ar.gob.anses.mercosur.base.service.test;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.xml.sax.SAXException;

import ar.com.jmc.webplatform.base.model.dto.generated.Beneficiario;
import ar.com.jmc.webplatform.base.model.dto.generated.BeneficiosSolicitados;
import ar.com.jmc.webplatform.base.model.dto.generated.Cabecera;
import ar.com.jmc.webplatform.base.model.dto.generated.Documento;
import ar.com.jmc.webplatform.base.model.dto.generated.Documentos;
import ar.com.jmc.webplatform.base.model.dto.generated.Domicilio;
import ar.com.jmc.webplatform.base.model.dto.generated.ObjectFactory;
import ar.com.jmc.webplatform.base.model.dto.generated.PersonaFisica;
import ar.com.jmc.webplatform.base.model.dto.generated.RespuestaVerificacionAlta;
import ar.com.jmc.webplatform.base.model.dto.generated.TipoEstadoCivil;
import ar.com.jmc.webplatform.base.model.dto.generated.TipoSexo;
import ar.com.jmc.webplatform.base.util.XMLUtils;

public class TestServices {

	private static final String BENEFICIARIO_FILE = "beneficiario.xml";
	//private static final String FILE_TEST = "/home/ltroconis/Descargas/siaciimportacinyexportacin/EmpadronamientoAltaBrasilUruguay.xml";
	
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
		String fecha = dateToString(date);
		return getFechaXMLGregorianCalendar(fecha);
	}

	
	private static Cabecera fillBeneficiario() {
		ObjectFactory factory = new ObjectFactory();

		Cabecera cabecera = factory.createCabecera();

		cabecera.setFechaGene(getFechaXMLGregorianCalendar(Calendar
				.getInstance().getTime()));

		cabecera.setCodEnvio(BigInteger.TEN);

		cabecera.setPaisGestor(BigInteger.valueOf(2));
		Beneficiario beneficiario = factory.createBeneficiario();

		PersonaFisica persona = factory.createPersonaFisica();

		persona.setApellidoMaterno("");
		persona.setNombreMadre("");
		persona.setApellidoPaterno("CHAVEZ");
		persona.setNombrePadre("");
		persona.setNombre("Mariana");
		persona.setNacionalidad(BigInteger.valueOf(3));
		persona.setFechaNacimiento(getFechaXMLGregorianCalendar("1956-02-05"));
		persona.setSexo(TipoSexo.F);
		persona.setEstadoCivil(TipoEstadoCivil.C);
		persona.setEstadoBeneficiario(BigInteger.valueOf(2));
		persona.setFechaEstado(getFechaXMLGregorianCalendar("2013-11-01"));

		beneficiario.setPersonaFisica(persona);

		Domicilio domicilio = factory.createDomicilio();
		domicilio.setCalle("R. Ferreira Viana");
		domicilio.setNumero("350");
		domicilio.setPiso("");
		domicilio.setDepartamento("");
		domicilio.setCodigoPostal("222221");
		domicilio.setLocalidad("rio janeiro");
		domicilio.setProvinciaEstadoDepartamento("rio janeiro");
		domicilio.setTelefono("");
		domicilio.setCorreoElectronico("mariana@gmail.com");

		beneficiario.setDomicilio(domicilio);

		BeneficiosSolicitados beneficiosSolicitados = factory
				.createBeneficiosSolicitados();

		beneficiario.getBeneficiosSolicitados().add(beneficiosSolicitados);

		Documento doc = factory.createDocumento();

		doc.setPaisOrigenDocumento(BigInteger.valueOf(4));
		doc.setTipoDocumento(BigInteger.valueOf(4));
		doc.setNumeroDocumento("12558963");

		Documentos dosc = factory.createDocumentos();
		dosc.getDocumento().add(doc);

		beneficiario.getDocumentos().add(dosc);

		RespuestaVerificacionAlta resp = factory
				.createRespuestaVerificacionAlta();
		resp.setEstadoVerificacion(BigInteger.ONE);
		resp.setMotivo("01-Doc. Existente");

		beneficiario.setRespuestaVerificacionAlta(resp);

		cabecera.getBeneficiario().add(beneficiario);
		cabecera.getBeneficiario().add(beneficiario);

		return cabecera;
	}

	@Test
	public void mapeoObjectDtoDelXSD() throws JAXBException, IOException {

		JAXBContext context = JAXBContext.newInstance(Cabecera.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		Cabecera cabecera = fillBeneficiario();

		// Mostramos el documento XML generado por la salida estandar
		marshaller.marshal(cabecera, System.out);

		FileOutputStream fos = new FileOutputStream(BENEFICIARIO_FILE);
		// guardamos el objeto serializado en un documento XML
		marshaller.marshal(cabecera, fos);
		fos.close();

		Unmarshaller unmarshaller = context.createUnmarshaller();
		// Deserealizamos a partir de un documento XML
		Cabecera provincaAux = (Cabecera) unmarshaller.unmarshal(new File(
				BENEFICIARIO_FILE));
		System.out
				.println("********* Beneficiario cargado desde fichero XML***************");
		// Mostramos por linea de comandos el objeto Java obtenido
		// producto de la deserialziacion
		marshaller.marshal(provincaAux, System.out);

	}
	
	@Test
	public void validarXMLbyXSD() throws MalformedURLException,
			SAXException, URISyntaxException {
		URL urlXSD = TestServices.class.getClassLoader().getResource("EsquemaEmpadronamientoAlta.xsd");
		URI fileValidar = TestServices.class.getClassLoader().getResource("EmpadronamientoAltaBrasilUruguay.xml").toURI();

		File file = new File(fileValidar);
		XMLUtils.validXML(urlXSD, file);
	}

	@Test
	public void importServicioRest()
			throws ClientProtocolException, IOException, URISyntaxException {
		URI fileValidar = TestServices.class.getClassLoader().getResource("EmpadronamientoAltaBrasilUruguay.xml").toURI();
		
		ObjectMapper mapper = new ObjectMapper();		
		
		String login = "http://localhost:8180/siaci/rest/user/validate";
		String beneficiarioImport = "http://localhost:8180/siaci/rest/beneficiario/import";
		
		//Autenticar 
		HttpClient httpclient = new DefaultHttpClient();
		
		CookieStore cookieStore = new BasicCookieStore();
		HttpContext httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		
		HttpPost httpPost = new HttpPost(login);

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("username", "admin"));
		formparams.add(new BasicNameValuePair("password", "admin"));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams,
				"UTF-8");
		httpPost.setEntity(formEntity);
		
		HttpResponse response = null;
		
		try{
		  response = httpclient.execute(httpPost,httpContext);
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Asegurece de tener levantados los servicios");
			return;
		}
		
		HttpEntity entity = response.getEntity();
		
		// Get the contents of the response
		InputStream input = entity.getContent();
		String responseBody = IOUtils.toString(input);
		input.close();			
		
		int statusCode = response.getStatusLine().getStatusCode();

		//Tomo el token para importar 
		JsonNode json = mapper.readValue(responseBody,JsonNode.class);
		String token = json.get("token").asText();
		// Print the response code and message body
		System.out.println("HTTP Status Code: " + statusCode);
		System.out.println(responseBody);

		//Importar 
		httpPost = new HttpPost(beneficiarioImport);
		httpPost.setHeader("Auth-Token",token);
		httpPost.setHeader("charset","UTF-8");

		FileBody uploadFilePart = new FileBody(new File(fileValidar));
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("file", uploadFilePart);
		httpPost.setEntity(reqEntity);
		
		try{
			response = httpclient.execute(httpPost,httpContext);
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Asegurece de tener levantados los servicios");
			return;
		}
		
		HttpEntity resEntity = response.getEntity();

		// Get the HTTP Status Code
		statusCode = response.getStatusLine().getStatusCode();

		// Get the contents of the response
		input = resEntity.getContent();
		responseBody = IOUtils.toString(input);
		input.close();

		// Print the response code and message body
		System.out.println("HTTP Status Code: " + statusCode);
		System.out.println(responseBody);
	}
}
