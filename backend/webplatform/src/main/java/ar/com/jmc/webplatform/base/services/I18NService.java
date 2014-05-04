package ar.com.jmc.webplatform.base.services;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public interface I18NService {
	
	//String getMensaje(String codigo);
	//String getMensaje(String codigo, Object... parametros);
	
	String getMensaje(String codigo,Locale locale);
	String getMensaje(String codigo,Locale locale, Object... parametros);
	
	HashMap<String, String> getLanguageModule(String lang, String module);
	HashMap<String, String> getDefaultLanguage();
	HashMap<String, String> getInformationLanguage(String lang);
	List<HashMap<String, String>> getOthersLanguages(String lang);
}
