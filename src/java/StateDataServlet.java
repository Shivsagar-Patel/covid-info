import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StateDataServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        PrintWriter out=response.getWriter();
           //this servlet show the data of the state whose admin in login in
     //from where the name of state will come?(session)
       HttpSession session=request.getSession();
      String state=(String)session.getAttribute("state");
      
        Connection con=mypkg.Utility.connect();
        String sql="select * from covidinfo where state=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,state);
        ResultSet rs=ps.executeQuery();
        
   out.println("<html><body>");
   out.println("<hr>");
   out.println("<h3>Data For State :"+state+"</h3>");
   out.println("<table border=2>");
   out.println("<tr>");
   out.println("<th>Sno</th><th>Date</th><th>Total</th><th>Active</th><th>Deaths</th>");
   out.println("</tr>");
   while(rs.next())
   {
   
       String sno=rs.getString("sno");
       String date=rs.getString("idate");
       String total=rs.getString("total");
       String active=rs.getString("active");
       String deaths=rs.getString("deaths");
       out.println("<tr>");
       out.println("<td>"+sno+"</td>");
       out.println("<td>"+date+"</td>");
       out.println("<td>"+total+"</td>");
       out.println("<td>"+active+"</td>");
       out.println("<td>"+deaths+"</td>");
       out.println("</tr>");
       
   }
   out.println("</table");
   out.println("<hr>");
   out.println("<h4><a href=Stateadmindashboard.jsp>Dashboard</h4>");
   out.println("</body></html>");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
