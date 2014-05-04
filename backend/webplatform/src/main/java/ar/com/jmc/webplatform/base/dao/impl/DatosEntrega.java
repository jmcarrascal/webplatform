/*
 * Usuarios.java
 *
 * Created on 21 de septiembre de 2003, 12:14
 */

package ar.com.jmc.webplatform.base.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.jmc.webplatform.base.model.Entrega;


/**
 *
 * @author  JMC y Andres Yebra
 */
public class DatosEntrega {
    
    public DatosEntrega() {
    	try {
			this.setConnNueva(getConnection()) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private Connection connNueva; 
    
    public Connection getConnNueva() {
		return connNueva;
	}



	public void setConnNueva(Connection connNueva) {
		this.connNueva = connNueva;
	}



	public Connection getConnection() throws Exception {

    String system           = "192.1.1.1";
    //String system           = "www.coinse.com.ar";
    String collectionName   = "TRFFILLIB";
    String tableName        = "TFSMSE00";
    System.out.println(system);

    Connection connection   = null;

    try {
        
        Class.forName("com.ibm.as400.access.AS400JDBCDriver");	
        
        connection = DriverManager.getConnection ("jdbc:as400://"
        + system + "/" + collectionName,"qsecofer","c2259");
        //+ system + "/" + collectionName,"jmc","jmc");
         
        return connection;
    }catch(Exception e){
        System.out.println(e.toString());
    }  
    return connection;
    }

    
    
    public List<Entrega> getZonasPorEquipo(int nroEquipo) throws Exception {
        List<Entrega> entregas = new ArrayList<Entrega>();
        try {            
             System.out.println("Empiezo a pedir los datos");
            
            
         
             
            Connection conn = getConnNueva();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT SMNRVI,SMEQUI,SMPUTR,SMLOCA " +
                                //"FROM TFSMSE00 WHERE SMEQUI = ? ");
                                "FROM TFSMSE00 WHERE SMEQUI = ? AND SMHORE = 0");
            stmt.setInt (1,nroEquipo);
            System.out.println("Antes Query");
            ResultSet rs = stmt.executeQuery();            
            System.out.println("DEspu Query");            
            boolean notnull = false;                      
            while (rs.next()) {
                Entrega entrega = new Entrega();        
                entrega.setNroViaje(rs.getInt("SMNRVI"));
                entrega.setNroEquipo(rs.getInt("SMEQUI"));
                entrega.setLocalidad_id(rs.getInt("SMPUTR"));
                entrega.setLocalidad(rs.getString("SMLOCA"));
                entrega.setViajeLocalidad(rs.getInt("SMNRVI") + " " + rs.getString("SMLOCA"));
                entrega.setNroViajeLocalidad_id(rs.getInt("SMNRVI") + "," + rs.getInt("SMPUTR"));
                System.out.println("Viaje Localidad " + entrega.getViajeLocalidad());
                entregas.add(entrega);
                notnull= true;
            }
            if (notnull==false){
                entregas = null;
            }
            rs.close();
            stmt.close();
            conn.close();
            
        }
        catch (Exception e) {
            System.out.println(e.toString());            
        }
        
        return entregas;
    }
    
    
    public Entrega getEntregaPorNroViaje(int nroViaje) throws Exception {
        Entrega entrega = new Entrega();
        try {            
             System.out.println("Empiezo a pedir los datos");
            
            Connection conn = getConnNueva();
            
            PreparedStatement stmt = conn.prepareStatement("SELECT SMNRVI,SMEQUI,SMPUTR,SMLOCA " +
                                //"FROM TFSMSE00 WHERE SMEQUI = ? ");
                                "FROM TFSMSE00 WHERE SMNRVI = ?");
            stmt.setInt (1,nroViaje);
            System.out.println("Antes Query");
            ResultSet rs = stmt.executeQuery();            
            System.out.println("DEspu Query");            
                          
            if (rs.next()) {
                        
                entrega.setNroViaje(rs.getInt("SMNRVI"));
                entrega.setNroEquipo(rs.getInt("SMEQUI"));
                entrega.setLocalidad_id(rs.getInt("SMPUTR"));
                entrega.setLocalidad(rs.getString("SMLOCA"));
                entrega.setViajeLocalidad(rs.getInt("SMNRVI") + " " + rs.getString("SMLOCA"));
                entrega.setNroViajeLocalidad_id(rs.getInt("SMNRVI") + "," + rs.getInt("SMPUTR"));
                System.out.println("Viaje Localidad " + entrega.getViajeLocalidad());
                
            }
            
            rs.close();
            stmt.close();
            //conn.close();
            
        }
        catch (Exception e) {
            System.out.println(e.toString());            
        }
        
        return entrega;
    }
    public void aceptarEntrega(int viaje_id, int localidad_id, int fechaActual, int horaActual)throws SQLException{
        try{
        Connection con = getConnNueva();  
        
        
        
        PreparedStatement pstmt = con.prepareStatement("update TFSMSE00 set SMFERE = ?, SMHORE = ?" +
                " WHERE SMNRVI = ? AND SMPUTR = ?");
        pstmt.setInt(1, fechaActual);
        pstmt.setInt(2, horaActual);
        pstmt.setInt(3, viaje_id);
        pstmt.setInt(4,  localidad_id);        
        pstmt.executeUpdate();
        pstmt.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
