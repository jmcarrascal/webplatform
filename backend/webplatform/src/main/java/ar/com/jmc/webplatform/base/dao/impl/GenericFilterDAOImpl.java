package ar.com.jmc.webplatform.base.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.dao.DataAccessException;

import ar.com.jmc.webplatform.base.dao.GenericFilterDAO;
import ar.com.jmc.webplatform.base.model.filter.GenericFilter;
import ar.com.jmc.webplatform.base.util.exception.DataAccessDPIv1Exception;

public abstract class GenericFilterDAOImpl<T> extends GenericDAOImpl<T>
		implements GenericFilterDAO<T> {
	
	protected GenericFilterDAOImpl(Class<T> clazz) {
		super(clazz);
	}

	// TODO buscar forma de no tener que castear el filter en las clases hijas
	protected abstract Criteria buildFilterCriteria(GenericFilter<T> filter);
	
	@SuppressWarnings("unchecked")
	public List<T> findByFilter(GenericFilter<T> filter)
			throws DataAccessDPIv1Exception {
		try {
			Criteria criteria = buildFilterCriteria(filter);
			
			// FIXME temporariamente se pagina desde el cliente
			/*Integer pagina = filter.getPagina();
			if (pagina == null || pagina <= 0) {
				pagina = 1;
			}
			Integer registrosPorPagina = filter.getRegistrosPorPagina();
			if (registrosPorPagina == null || registrosPorPagina <= 0) {
				registrosPorPagina = 10;
			}
			
			//criteria.setFirstResult(registrosPorPagina * (pagina - 1));
			//criteria.setMaxResults(registrosPorPagina);*/
			
			return criteria.list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DataAccessDPIv1Exception(DataAccessDPIv1Exception.GET_ERROR, e);
		}
	}


}
