package ar.com.jmc.webplatform.base.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.jmc.webplatform.base.dao.LogDAO;
import ar.com.jmc.webplatform.base.model.Log;
import ar.com.jmc.webplatform.base.model.Usuario;
import ar.com.jmc.webplatform.base.model.message.JsonResult;
import ar.com.jmc.webplatform.base.services.LogService;


@Service
public class LogServiceImpl implements LogService{

	
	@Autowired
	private LogDAO logDAO;
	
	
	
	public String setLog(Log log) {
		logDAO.setLog(log);
		return "OK";
	}



	
	
}
