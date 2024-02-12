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

public class AllStateData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        PrintWriter out=response.getWriter();
           //this servlet show the data of the state whose admin in login in
     //from where the name of state will come?(session)
//       HttpSession session=request.getSession();
//      String state=(String)session.getAttribute("state");

      
        Connection con=mypkg.Utility.connect();
        String sql="select * from covidinfo";
       PreparedStatement ps=con.prepareStatement(sql);
        //ps.setString(1,state);
        ResultSet rs=ps.executeQuery();
        
   out.println("<html><body>");
   out.println("<hr>");
   out.println("<h3>Data For  All States</h3>");
   out.println("<table border=2>");
   out.println("<tr>");
   out.println("<th>Sno</th><th>State</th><th>Date</th><th>Total</th><th>Active</th><th>Deaths</th>");
   out.println("</tr>");
   int sumtotal=0;
   int sumactive=0;
   int sumdeath=0;
   while(rs.next())
   {
   
       String sno=rs.getString("sno");
       String state=rs.getString("state");
       String date=rs.getString("idate");
       int total=rs.getInt("total");sumtotal=sumtotal+total;
       int active=rs.getInt("active");sumactive=sumactive+active;
       int deaths=rs.getInt("deaths");sumdeath=sumdeath+deaths;
       out.println("<tr>");
       out.println("<td>"+sno+"</td>");
       out.println("<td>"+state+"</td>");
       out.println("<td>"+date+"</td>");
       out.println("<td align=right>"+total+"</td>");
       out.println("<td align=right>"+active+"</td>");
       out.println("<td align=right>"+deaths+"</td>");
       out.println("</tr>");
       
   }
   out.println("<tr>");
   out.println("<td></td><td></td><td></td>");
   out.println("<td align=right>"+sumtotal+"</td>");
   out.println("<td align=right>"+sumactive+"</td>");
   out.println("<td align=right>"+sumdeath+"</td>");
   out.println("</tr>");

   
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
