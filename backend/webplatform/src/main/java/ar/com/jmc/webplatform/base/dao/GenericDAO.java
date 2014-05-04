package ar.com.jmc.webplatform.base.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import ar.com.jmc.webplatform.base.util.exception.DataAccessDPIv1Exception;



public interface GenericDAO<T> {

	public void save(T newInstance) throws DataAccessException;

	public void update(T persistenceInstance) throws DataAccessException;

	public void remove(T persistenceInstance) throws DataAccessException;

	public List<T> getAll() throws DataAccessException;

	public T getByPrimaryKey(Long id) throws DataAccessException;

	public List<T> findByObjectCriteria(T object) throws DataAccessException;

	public List<T> findByObjectCriteria(HashMap<String, Object> res)
			throws DataAccessException;

	public void saveOrUpdate(T newInstance) throws DataAccessException;
	
	public T getByPrimaryKey(String id) throws DataAccessDPIv1Exception;

	public T getByPrimaryKey(Integer id) throws DataAccessDPIv1Exception;
	
	public T getByPrimaryKey(Character id) throws DataAccessDPIv1Exception;
}
