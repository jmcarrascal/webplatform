package ar.com.jmc.webplatform.base.dao;

import ar.com.jmc.webplatform.base.model.Usuario;




public interface UsuarioDAO extends GenericDAO<Usuario> {

	Usuario getUserbyUserPass(Usuario usuario);
	
	Usuario getByUsername(String username);
	
}
