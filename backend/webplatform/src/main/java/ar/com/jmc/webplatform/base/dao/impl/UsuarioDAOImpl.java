package ar.com.jmc.webplatform.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.jmc.webplatform.base.dao.UsuarioDAO;
import ar.com.jmc.webplatform.base.model.Usuario;

@Repository
@Transactional
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UsuarioDAOImpl() {
		super(Usuario.class);
	}

	
	@SuppressWarnings("unchecked")
	public Usuario getUserbyUserPass(Usuario usuarioIn) {
		Usuario usuario = null;
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		if (usuarioIn.getUsername() != null && !usuarioIn.getUsername().trim().equals("")  && !usuarioIn.getPassword().trim().equals("")){
			usuarioList = sessionFactory.getCurrentSession()
		.createQuery("select u from Usuario u where u.nombreUsuario = :userName and password = :password")
		.setParameter("userName", usuarioIn.getUsername())
		.setParameter("password", usuarioIn.getPassword())
		.list();
		}
		if (usuarioList != null && usuarioList.size() > 0){
			usuario = usuarioList.get(0);
		}
		return usuario;

	}

	
	
	public Usuario getByUsername(String username) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("username", username));
		return Usuario.class.cast(criteria.uniqueResult());
	}
}
