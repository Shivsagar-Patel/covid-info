
package mypkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TotalCasesHandler extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
      try{
          String sql="select sum(total) from covidinfo";
          Connection con=mypkg.Utility.connect();
          PreparedStatement ps=con.prepareStatement(sql);
          ResultSet rs=ps.executeQuery(); 
          rs.next();
          String TotalCases=rs.getString(1);
          con.close();
          out.println("<h4>Total Covid Cases="+TotalCases+"</h4>");
      }
      catch(Exception e)
      {
          
      }
          
    }
    
}
