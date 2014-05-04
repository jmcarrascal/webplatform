package ar.com.jmc.webplatform.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.jmc.webplatform.base.services.I18NService;

@Controller
@Transactional
public class I18nController {
	
	@Autowired
	private I18NService i18NService;
	
	@RequestMapping(value="/i18n/getLanguage/{lang}/{module}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getLanguage(@PathVariable String lang, @PathVariable String module) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = i18NService.getLanguageModule(lang, module);

		return map;
	}
	
	@RequestMapping(value="/i18n/getDefaultLanguage", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getDefaultLanguage() {
		HashMap<String, String> map = new HashMap<String, String>();
		map = i18NService.getDefaultLanguage();

		return map;
	}
	
	@RequestMapping(value="/i18n/getOthersLanguages/{lang}", method = RequestMethod.GET)
	public @ResponseBody List<HashMap<String, String>> getOthersLanguages(@PathVariable String lang) {
		List<HashMap<String, String>> listLanguages = new ArrayList<HashMap<String, String>>(); 
		listLanguages = i18NService.getOthersLanguages(lang);

		return listLanguages;
	}
	
	@RequestMapping(value="/i18n/getInformationLanguage/{lang}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> getInformationLanguage(@PathVariable String lang) {
		HashMap<String, String> map = new HashMap<String, String>(); 
		map = i18NService.getInformationLanguage(lang);

		return map;
	}
	
}

