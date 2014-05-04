/*
 * JDBCPopulate.java
 *
 * Created on 26 de octubre de 2006, 03:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ar.com.jmc.webplatform.base.services.impl.test;

/**
 *
 * @author Juan Manuel
 */
//////////////////////////////////////////////////////////////////////////////////
//
// JDBCPopulate example.  This program uses the AS/400 JDBC driver to
// create and populate a table.
//
// Command syntax:
//    JDBCPopulate system collectionName tableName
//
// For example,
//    JDBCPopulate MySystem MyLibrary MyTable
//
//////////////////////////////////////////////////////////////////////////////////
//
// This source is an example of AS/400 Toolbox for Java JDBC driver.
// IBM grants you a nonexclusive license to use this as an example
// from which you can generate similar function tailored to
// your own specific needs.
//
// This sample code is provided by IBM for illustrative purposes
// only. These examples have not been thoroughly tested under all
// conditions. IBM, therefore, cannot guarantee or imply
// reliability, serviceability, or function of these programs.
//
// All programs contained herein are provided to you "AS IS"
// without any warranties of any kind.  The implied warranties of
// merchantablility and fitness for a particular purpose are
// expressly disclaimed.
//
// AS/400 Toolbox for Java
// (C) Copyright IBM Corp. 1997
// All rights reserved.
// US Government Users Restricted Rights -
// Use, duplication, or disclosure restricted
// by GSA ADP Schedule Contract with IBM Corp.
//
//////////////////////////////////////////////////////////////////////////////////



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
public class JDBCPopulate
{
	public static void main (String[] parameters)
	{
	String system           = "www.transfurlong.com.ar";
	String collectionName   = "TRFFILLIB";
	String tableName        = "TFPDCOCO00";

        System.out.println(system);

		Connection connection   = null;

		try {

			// Load the AS/400 Toolbox for Java JDBC driver.
                    Class.forName("com.ibm.as400.access.AS400JDBCDriver");	
                    //DriverManager.registerDriver(new AS400JDBCDriver());

			// Get a connection to the database.  Since we do not
			// provide a user id or password, a prompt will appear.
			//
			// Note that we provide a default schema here so
			// that we do not need to qualify the table name in
			// SQL statements.
			//
			
			connection = DriverManager.getConnection ("jdbc:as400://"
				+ system + "/" + collectionName,"qsecofer","c2259");
			

			// Drop the table if it already exists.
			try {
				Statement dropTable = connection.createStatement ();
				//dropTable.executeUpdate ("DROP TABLE " + tableName);
			}
			catch (SQLException e) {
				// Ignore.
			}

			// Create the table.
			Statement select = connection.createStatement ();
			//createTable.executeUpdate ("DELETE FROM " + tableName
			//	+ " WHERE I = 2 ");
			
                        ResultSet rs = select.executeQuery ("select * from " + tableName);
			while (rs.next( )) {
				System.out.println("uno " + rs.getString(1)) ;
			}
                        
			// Prepare a statement for inserting rows.  Since we
			// execute this multiple times, it is best to use a
			// PreparedStatement and parameter markers.
			//PreparedStatement insert = connection.prepareStatement ("UPDATE " + tableName + " SET CCRESP = 'PR' ");
                        
                       //PreparedStatement insert = connection.prepareStatement("select * from " + tableName);
        
                        //insert.executeUpdate ();
                        
                    String archivo = "02898yh";
                    String subarchivo = archivo.substring(0,2);
                    System.out.println("Sub " + subarchivo);                        
                    if (subarchivo.equals("02")){
                        System.out.println(archivo);                        
                    }else{
                        System.out.println(archivo);                                               
                    }
                        
				// Populate the table.
                        /*        
                        for (int i = 1; i <= words.length; ++i) {
				insert.setInt (1, i);
				insert.setString (2, words[i-1]);
				insert.setInt (3, i*i);
				insert.setDouble (4, Math.sqrt(i));
				
			}
			*/
			// Output a completion message.
			System.out.println ("Table " + collectionName + "." + tableName
				+ " has been populated.");
		}

		catch (Exception e) {
			//System.out.println (e.printStackTrace());
                        System.out.println (e.toString());
			System.out.println ("ERROR: " + e.getMessage());
		}

		finally {

			// Clean up.
			try {
				if (connection != null)
					connection.close ();
			}
			catch (SQLException e) {
				// Ignore.
			}
		}

		System.exit (0);
	}
}