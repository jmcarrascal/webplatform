package ar.com.jmc.webplatform.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.jmc.webplatform.base.dao.LogDAO;
import ar.com.jmc.webplatform.base.model.Log;
import ar.com.jmc.webplatform.base.model.Usuario;

@Repository
@Transactional
public class LogDAOImpl extends GenericDAOImpl<Log> implements LogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public LogDAOImpl() {
		super(Log.class);
	}

	public String setLog(Log log) {
		try{
			save(log);
		}catch(Exception e){
			e.printStackTrace();
			return "Error";
		}
		return "OK";
	}

	
}

