import java.sql.*;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;
import java.lang.Object.*;




public class Query4 {

   public static void main (String args []) {
    try
    {

        //Connection to Oracle server
        OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("ndandek1", "Ngd530128");

        //Prepare to call stored procedure:
        CallableStatement cs = conn.prepareCall("begin ? := StudentRegistration.showfunction_query4(?); end;");
        cs.setString(2,"B033");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next() && rs!=null) {
            System.out.println(
                rs.getString(1) + "\t" +
                rs.getString(2) + "\t" + 
                rs.getString(3) + "\t" +
                rs.getString(4) + "\t" + 
                rs.getString(5) + "\t" +
                rs.getString(6) + "\t" +
                rs.getInt(7) + "\t" +
                rs.getString(8)
                );
        }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
   } 
   // catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   // catch (Exception e) {System.out.println (e.getMessage());}
   catch (SQLException ge) 
{
 
     

        // *****************************************************************
        // NOTE: THIS will be where you check for your customer error code.
        // *****************************************************************
        if(ge.getErrorCode() == -20001) 
        {
           System.out.println("Error found");
        }
        else if(ge.getErrorCode() == -20002) 
        {
           System.out.println("Error found");
        }
        else
        {
            System.out.println(ge.getMessage());
       //     throw ge; // do not swallow unhandled exceptions
        }
}
  }
} 


