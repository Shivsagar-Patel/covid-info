
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateStateAdminProfile extends HttpServlet {
   
    private Connection con;
    private PreparedStatement ps;
 
    //Called when driver is loaded
    public void init()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid", "root", "root");
        String sql = "update stateadmins set password=?,uname=?,email=?,address=?,mobile=?,status='enabled' where userid=?";
        ps = con.prepareStatement(sql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
 
    }
    //called just before driver unloaded
    public void destroy()
    {
      try
      {
          con.close();
      }  
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out=response.getWriter();
        //reads the data
        // password=?,uname=?,email=?,address=?,mobile=?,status='enabled' where userid=?
        String userid=request.getParameter("userid");
        String password=request.getParameter("pwd");
        String uname=request.getParameter("username");
        String email=request.getParameter("Email");
        String address=request.getParameter("Address");
        String mobile=request.getParameter("Mobile"); 
      try {
          
              
              ps.setString(1,password);
              ps.setString(2,uname);
              ps.setString(3,email);
              ps.setString(4,address);
              ps.setString(5,mobile);
              ps.setString(6, userid);
              ps.executeUpdate();

    
          //provide response
             response.sendRedirect("Stateadmindashboard.jsp");
             
    
       

} catch (Exception e) {
            e.printStackTrace();
          
        }
   //     out.close();
        
        //provide response
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
