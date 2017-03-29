import java.sql.*;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;

public class Query9 {

   public static void main (String args []) {
    try
    {

        //Connection to Oracle server
        OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("ndandek1", "Ngd530128");

        //Prepare to call stored procedure:
        CallableStatement cs = conn.prepareCall("begin StudentRegistration.deleteStudent(?); end;");
        //register the out parameter (the first parameter)
        cs.setString(1, "B032");
        // cs.setString(3, "Desai");
        // cs.setString(4, "freshman");
        // cs.setDouble(5, 4.0);
        // cs.setString(6, "shruti@bing.edu");
        
        
        // execute and retrieve the result set
        cs.execute();
        // ResultSet rs = (ResultSet)cs.getObject(1);

        // // print the results
        // while (rs.next()) {
        //     System.out.println(rs.getString(1) + "\t" +
        //         rs.getString(2) + "\t" + rs.getString(3) + 
        //         rs.getString(4) + 
        //         "\t" + rs.getDouble(5) + "\t" +
        //         rs.getString(6));
        // }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
   } 
      catch (SQLException ge) 
{
        // *****************************************************************
        // NOTE: THIS will be where you check for your customer error code.
        // *****************************************************************
        if(ge.getErrorCode() == -20001) 
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


