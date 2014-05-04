package ar.com.jmc.webplatform.base.dao;

import ar.com.jmc.webplatform.base.model.Log;
import ar.com.jmc.webplatform.base.model.Usuario;




public interface LogDAO extends GenericDAO<Log> {

	String setLog(Log log);
}
