
package mypkg;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NewServletListener implements ServletContextListener {
             private static Connection con;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/covid","root","root");
             System.out.println("Connected Succesfully in Context Intialised....."+con);
             //how we can make this connection  object available for whole application
             //we aer storing this con object to cintext so that servlets can acces it from context obj
             ServletContext context=sce.getServletContext();
             //storing connection object to context
             context.setAttribute("dbcon",con);
        }
        catch(Exception e)
        {
            System.out.println("Exception in Connection..........."+e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
         try
        {
            con.close();
            System.out.println("Connection Closed Successfully in Context Destroyed");
        }
        catch(Exception e)
        {
            System.out.println("Exception in Connection..........."+e);
        }
    }
}
