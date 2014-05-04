package ar.com.jmc.webplatform.base.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.jmc.webplatform.base.model.Log;
import ar.com.jmc.webplatform.base.model.Usuario;
import ar.com.jmc.webplatform.base.model.message.JsonResult;
import ar.com.jmc.webplatform.base.services.LogService;
import ar.com.jmc.webplatform.base.services.UsuarioService;
import ar.com.jmc.webplatform.base.util.TokenUtils;
import ar.com.jmc.webplatform.security.UserTransfer;


@Controller
@Transactional
public class RESTController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsService userService;
	
	@RequestMapping(value="/usuario/validate", method = RequestMethod.POST)
	public @ResponseBody JsonResult validateUser(Locale locale,Usuario usuario) {
		 return usuarioService.validateUser(usuario);
	}
	
	@RequestMapping(value="/loginOut", method = RequestMethod.GET)
	public @ResponseBody String loginOut(Locale locale) {
		// FIXME completar este metodo
		 return "out";
	}
	
	@PreAuthorize("hasRole('avanzado')")
	@RequestMapping(value="/testLog", method = RequestMethod.GET)
	public @ResponseBody String testLog(Locale locale) {
		Usuario u = usuarioService.getCurrentUser();
		Log log = new Log();
		log.setDateMovimiento(new Timestamp(System.currentTimeMillis()));
		log.setErrorDescrip("algo");
		log.setIdUsuario(u.getIdUsuario());
		log.setTipoMovimiento("10");
		logService.setLog(log);
		
		return "OK";
	}
	
	@RequestMapping(value="/user/validate", method = RequestMethod.POST)
	public @ResponseBody UserTransfer authenticate(Locale locale,Usuario usuario) {
		
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Map<String, Boolean> roles = new HashMap<String, Boolean>();

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
		UserDetails userDetails = this.userService.loadUserByUsername(usuario.getUsername());

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.toString(), Boolean.TRUE);
		}

		return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
	}

		
}