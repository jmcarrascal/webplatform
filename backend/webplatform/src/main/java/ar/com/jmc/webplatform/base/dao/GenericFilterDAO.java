package ar.com.jmc.webplatform.base.dao;

import java.util.List;

import ar.com.jmc.webplatform.base.model.filter.GenericFilter;
import ar.com.jmc.webplatform.base.util.exception.DataAccessDPIv1Exception;

public interface GenericFilterDAO<T> extends GenericDAO<T> {

	List<T> findByFilter(GenericFilter<T> genericFilter) throws DataAccessDPIv1Exception;

}
