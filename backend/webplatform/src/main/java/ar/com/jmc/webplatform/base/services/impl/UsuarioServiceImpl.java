package ar.com.jmc.webplatform.base.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.jmc.webplatform.base.dao.UsuarioDAO;

import ar.com.jmc.webplatform.base.model.Usuario;
import ar.com.jmc.webplatform.base.model.message.JsonResult;

import ar.com.jmc.webplatform.base.services.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService{

	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	
	@Transactional
	public Boolean validateUsuario() {				
		return true;
	}

	@Override
	public List<Usuario> geAll() {		
		return usuarioDAO.getAll();
	}

	@Override
	public void newUser(Usuario usuario) {
		usuarioDAO.save(usuario);	
	}

	
	public JsonResult validateUser(Usuario usuarioIn) {
		JsonResult jsonResult = new JsonResult(false,null);
		Usuario usuario = usuarioDAO.getUserbyUserPass(usuarioIn);
		if (usuario == null){
			jsonResult = new JsonResult(false,"Error en la autenticacion");
		}else{
			jsonResult = new JsonResult(true,"Nombre Usuario: " + usuario.getUsername());
		}
		return jsonResult;
		
	}
	
	public Usuario getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    return usuarioDAO.getByUsername(auth.getName());
	}


	public Usuario findByUsernameAndCodigoPais(String username,
			String codigoPais) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
