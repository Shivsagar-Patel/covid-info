
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationServletEx extends HttpServlet {
   
    private Connection con;
    private PreparedStatement ps;
 
    //Called when driver is loaded
    public void init()
    {
        try{
//        Class.forName("com.mysql.jdbc.Driver");
//        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid", "root", "root");
        ServletContext context=getServletContext();
        con=(Connection)context.getAttribute("dbcon");
                
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
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
    
    }

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out=response.getWriter();
        //reads the data
        String Userid=request.getParameter("uid");
        String Password=request.getParameter("password");
        String Name=request.getParameter("name");
        String Address=request.getParameter("address");
        String Mobile=request.getParameter("mobile");
        //process  or save the data
//        out.println(Userid);
//        out.println(Password);
//        out.println(Name);
//        out.println(Address);
//        out.println(Mobile);
        
      try {
//       Class.forName("com.mysql.jdbc.Driver");
//       out.println("Driver loaded");
//
//    String jdbcUrl = "jdbc:mysql://localhost:3306/covid";
//    String username = "root";
//    String password = "root";
//    
//    Connection con = DriverManager.getConnection(jdbcUrl, username, password);
//    out.println("Connected");
//
//    String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
//    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1,Userid);
    ps.setString(2, Password);
    ps.setString(3, Name);
    ps.setString(4, Address);
    ps.setString(5, Mobile);
    ps.executeUpdate();
    //provide response
    out.println("<html>");
    out.println("<body>");
    out.println("<h3>Registration success</h3>");
     out.println("<h3><a href=index.jsp>Login</a></h3>");
    out.println("</html>");
    out.println("</body>");
    
       

} catch (Exception e) {
    out.println(e);
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
