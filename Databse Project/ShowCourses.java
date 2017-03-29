import java.sql.*;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;




public class ShowCourses {

   public static void main (String args []) throws SQLException {
    try
    {

        //Connection to Oracle server
        OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
        ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:ACAD111");
        Connection conn = ds.getConnection("ndandek1", "Ngd530128");

        //Prepare to call stored procedure:
        CallableStatement cs = conn.prepareCall("begin ? := StudentRegistration.show_courses(); end;");
        //register the out parameter (the first parameter)
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        
        
        // execute and retrieve the result set
        cs.execute();
        ResultSet rs = (ResultSet)cs.getObject(1);

        // print the results
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" +
                rs.getInt(2) + "\t" + rs.getString(3));
        }

        //close the result set, statement, and the connection
        cs.close();
        conn.close();
   } 
   catch (SQLException ex) { System.out.println ("\n*** SQLException caught ***\n" + ex.getMessage());}
   catch (Exception e) {System.out.println ("\n*** other Exception caught ***\n");}
  }
} 


