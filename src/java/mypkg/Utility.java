
package mypkg;


import java.sql.Connection;
import java.sql.DriverManager;

public class Utility
{
     public static Connection connect()throws Exception
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root", "root");
            return con;
        }
    
}