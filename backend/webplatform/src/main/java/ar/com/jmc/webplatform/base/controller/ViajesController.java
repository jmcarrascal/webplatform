package ar.com.jmc.webplatform.base.controller;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.jmc.webplatform.base.dao.impl.DatosEntrega;
import ar.com.jmc.webplatform.base.model.Entrega;
import ar.com.jmc.webplatform.base.model.Log;
import ar.com.jmc.webplatform.base.model.Usuario;
import ar.com.jmc.webplatform.base.model.message.JsonResult;
import ar.com.jmc.webplatform.base.services.I18NService;
import ar.com.jmc.webplatform.base.services.LogService;
import ar.com.jmc.webplatform.base.services.UsuarioService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@Transactional
public class ViajesController {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LogService logService;

	
	@Autowired
	private I18NService i18nService;
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	
	@PreAuthorize("hasRole('avisorecepcion')")
	@RequestMapping(value="/viaje_equipo/{id}", method=RequestMethod.GET)
	public @ResponseBody List<Entrega> getViajeEquipo(Locale locale, @PathVariable int id) {
		DatosEntrega datosEntrega = new DatosEntrega();
		try {
			return datosEntrega.getZonasPorEquipo(id);
		} catch (Exception e) {
			return new ArrayList<Entrega>();
		}
	}
	
	@PreAuthorize("hasRole('avisorecepcion')")
	@RequestMapping(value="/aceptar_viaje/{id}/{localidad_id}", method=RequestMethod.GET)
	public @ResponseBody JsonResult aceptar_viaje(Locale locale, @PathVariable int id, @PathVariable int localidad_id) {
		DatosEntrega datosEntrega = new DatosEntrega();
		try {
			
			//Entrega entrega = datosEntrega.getEntregaPorNroViaje(id);
			
			 //Establesco Formato fecha.
		    SimpleDateFormat fmt = new SimpleDateFormat("1yyMMdd");
		    //Establesto la fecha y la hora actual.
		    int fechaActual = Integer.parseInt(fmt.format(new Date()));

		    //Establesco Formato Hora.
		    SimpleDateFormat fmh = new SimpleDateFormat("HHmm");
		    //Establesto la fecha y la hora actual.
		    int horaActual = Integer.parseInt(fmh.format(new Date()));
		    //int horaActual = 101;
		    System.out.println("hora " + horaActual + "fecha " + fechaActual );
		    
		    datosEntrega.aceptarEntrega(id,localidad_id,fechaActual,horaActual);  
		    
		    Usuario u = usuarioService.getCurrentUser();
			Log log = new Log();
			log.setDateMovimiento(new Timestamp(System.currentTimeMillis()));
			log.setErrorDescrip(String.valueOf(id));
			log.setIdUsuario(u.getIdUsuario());
			log.setTipoMovimiento("10");
			
			logService.setLog(log);
			
			return new JsonResult(true, "OK");
		} catch (Exception e) {  
			return new JsonResult(true, "OK");
		}
	}
	
	
	
}
