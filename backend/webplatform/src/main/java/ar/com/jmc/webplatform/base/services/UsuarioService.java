package ar.com.jmc.webplatform.base.services;

import java.util.List;

import ar.com.jmc.webplatform.base.model.Usuario;
import ar.com.jmc.webplatform.base.model.message.JsonResult;





public interface UsuarioService {

	public Boolean validateUsuario();

	public List<Usuario> geAll();

	public void newUser(Usuario usuario);

	public JsonResult validateUser(Usuario usuarioIn);
	
	public Usuario getCurrentUser();
	
	public Usuario findByUsernameAndCodigoPais(String username, String codigoPais);
}
