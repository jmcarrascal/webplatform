/*
 * ConnAS44TF.java
 *
 * Created on 26 de octubre de 2006, 03:25 PM
 *
 */

package ar.com.jmc.webplatform.base.services.impl.test;

/**
 *
 * @author Juan Manuel
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

 
public class ConnAS44TF
{
	public static void main (String[] parameters)
	{
	String system           = "192.1.1.1";
	String collectionName   = "TRFFILLIB";
	String tableName        = "TFPDCOCO00";

        System.out.println(system);

		Connection conn  = null;

		try {

                // Load the AS/400 Toolbox for Java JDBC driver.
                Class.forName("com.ibm.as400.access.AS400JDBCDriver");	
                //DriverManager.registerDriver(new AS400JDBCDriver());
		
		conn = DriverManager.getConnection ("jdbc:as400://"
				+ system + "/" + collectionName,"qsecofer","c2259");               
                                
                InsertoCliente(conn);
                /*
                Statement select = conn.createStatement ();
			
                ResultSet rs = select.executeQuery ("select * from " + tableName);
			while (rs.next( )) {
				System.out.println("uno " + rs.getString(1)) ;
			}
                        
		System.out.println ("Table " + collectionName + "." + tableName
				+ " has been populated.");
		*/
                }
                
		catch (Exception e) {
			//System.out.println (e.printStackTrace());
                        System.out.println (e.toString());
			System.out.println ("ERROR: " + e.getMessage());
		}
                 

		finally {

			// Clean up.
			try {
				if (conn != null)
					conn.close ();
			}
			catch (SQLException e) {
				// Ignore.
			}
		}

		System.exit (0);
	}
        public static void InsertoCliente(Connection conn)throws Exception{
        try{
        //Connection con = base.getConnection();  
        PreparedStatement pstmt = conn.prepareStatement("insert into TFPDCOCO00 (PDCCCLIE,PDCCNRDE,PDCCPASS,PDCCVERS) values (?,?,?,?)");
        pstmt.setInt(1, 001);
        pstmt.setInt(2, 00006);
        pstmt.setString(3, "FORCORAU");
        pstmt.setString(4, "PEPE");
        pstmt.executeUpdate();
        pstmt.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

}