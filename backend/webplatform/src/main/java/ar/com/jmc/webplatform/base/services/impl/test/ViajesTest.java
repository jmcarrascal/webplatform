package ar.com.jmc.webplatform.base.services.impl.test;

import java.util.List;

import ar.com.jmc.webplatform.base.dao.impl.DatosEntrega;
import ar.com.jmc.webplatform.base.model.Entrega;

public class ViajesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosEntrega datosEntrega = new DatosEntrega();
		try {
			List<Entrega> entregas = datosEntrega.getZonasPorEquipo(1257);
			for(Entrega e : entregas){
				System.out.println(e.getNroEquipo());
				System.out.println(e.getLocalidad());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
