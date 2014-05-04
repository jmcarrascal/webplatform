package ar.com.jmc.webplatform.base.services.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import ar.com.jmc.webplatform.base.services.I18NService;

@Service
@Transactional
public class I18NServiceImpl implements I18NService {

	@Resource(name = "messageSource")
	private ResourceBundleMessageSource messageSource;

	public String getMensaje(String codigo, Locale locale, Object... parametros) {

		String mensajeBase = getMensaje(codigo, locale);
		if (parametros == null || parametros.length == 0) {
			return mensajeBase;
		}

		int index = 0;
		for (Object param : parametros) {
			mensajeBase = replaceParameter(mensajeBase, index, param);
			index++;
		}

		return mensajeBase;
	}

	private String replaceParameter(String mensaje, int index, Object value) {
		String token = "{" + index + "}";
		mensaje = mensaje.replace(token, value != null ? value.toString() : "");
		return mensaje;
	}

	@Override
	public String getMensaje(String codigo, Locale locale) {
		return messageSource.getMessage(codigo, null, locale);
	}

	
	
	
	@Override
	public HashMap<String, String> getLanguageModule(String lang, String module){
		HashMap<String, String> mapLanguage = new HashMap<String, String>();
		try {
			
			URL resourceUrl = getClass().getResource("/i18n/anses.mercosur."+lang+".xliff");
			File fXmlFile = new File(resourceUrl.toURI());
			
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		    
		    doc.getDocumentElement().normalize();

		    NodeList nListGroup = doc.getElementsByTagName("group");
		    for (int tempGroup = 0; tempGroup < nListGroup.getLength(); tempGroup++) {
		    	Node nNodeGroup = nListGroup.item(tempGroup);
		    	Element elementGroup = (Element) nNodeGroup;
		    	if (elementGroup.getAttribute("id").equals(module)){
		    		NodeList nList = nNodeGroup.getChildNodes();
		    		for (int temp = 0; temp < nList.getLength(); temp++) {
		    			Node nNode = nList.item(temp);
		    			if (nNode.getNodeName().equals("trans-unit")){
		    				mapLanguage.put(nNode.getChildNodes().item(1).getTextContent(), nNode.getChildNodes().item(3).getTextContent());
		    			}
		    		}
		    		break;
		    	} 
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
	    }
		
		return mapLanguage;

	}
	
	@Override
	public HashMap<String, String> getDefaultLanguage(){
		HashMap<String, String> mapLanguage = new HashMap<String, String>();
		try {
			
			URL resourceUrl = getClass().getResource("/i18n/anses.mercosur.languages.xml");
			File fXmlFile = new File(resourceUrl.toURI());
			
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		    
		    doc.getDocumentElement().normalize();

		    NodeList nList = doc.getElementsByTagName("language");
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		    	Node nNode = nList.item(temp);
		
		    	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    		Element element = (Element) nNode;
		    		if (element.getElementsByTagName("default").item(0).getTextContent().isEmpty()){
		    		    mapLanguage.put("tag", element.getElementsByTagName("tag").item(0).getTextContent());
		    		    mapLanguage.put("abrev", element.getElementsByTagName("abrev").item(0).getTextContent());
		    		    mapLanguage.put("flag", element.getElementsByTagName("flag").item(0).getTextContent());
		    		    break;
		    		}
				}
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
	    }
		
		return mapLanguage;

	}
	
	@Override
	public HashMap<String, String> getInformationLanguage(String lang){
		HashMap<String, String> mapLanguage = new HashMap<String, String>();
		try {
			URL resourceUrl = getClass().getResource("/i18n/anses.mercosur.languages.xml");
			File fXmlFile = new File(resourceUrl.toURI());
			
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		    
		    doc.getDocumentElement().normalize();

		    NodeList nList = doc.getElementsByTagName("language");
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		    	Node nNode = nList.item(temp);
		
		    	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    		Element element = (Element) nNode;
		    		if (element.getElementsByTagName("abrev").item(0).getTextContent().equals(lang)){
		    		    mapLanguage.put("tag", element.getElementsByTagName("tag").item(0).getTextContent());
		    		    mapLanguage.put("abrev", element.getElementsByTagName("abrev").item(0).getTextContent());
		    		    mapLanguage.put("flag", element.getElementsByTagName("flag").item(0).getTextContent());
		    		    break;
		    		}
				}
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
	    }
		
		return mapLanguage;

	}
	
	@Override
	public List<HashMap<String, String>> getOthersLanguages(String lang){
		List<HashMap<String, String>> listLanguages = new ArrayList<HashMap<String, String>>(); 
		try {
			
			URL resourceUrl = getClass().getResource("/i18n/anses.mercosur.languages.xml");
			File fXmlFile = new File(resourceUrl.toURI());
			
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		    
		    doc.getDocumentElement().normalize();

		    NodeList nList = doc.getElementsByTagName("language");
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		    	Node nNode = nList.item(temp);
		
		    	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    		Element element = (Element) nNode;		    	
		    		if (!element.getElementsByTagName("abrev").item(0).getTextContent().equals(lang)){
		    		    HashMap<String, String> mapLanguage = new HashMap<String, String>();
		    		    mapLanguage.put("tag", element.getElementsByTagName("tag").item(0).getTextContent());
		    		    mapLanguage.put("abrev", element.getElementsByTagName("abrev").item(0).getTextContent());
		    		    mapLanguage.put("flag", element.getElementsByTagName("flag").item(0).getTextContent());
		    		    listLanguages.add(mapLanguage);
		    		}
				}
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
	    }
		
		return listLanguages;

	}
	
}
