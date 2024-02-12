
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
import javax.servlet.http.HttpSession;


public class SaveInfo extends HttpServlet {
   
    private Connection con;
    private PreparedStatement ps;
 
    //Called when driver is loaded
    public void init()
    {
        try{
//        Class.forName("com.mysql.jdbc.Driver");
//        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid", "root", "root");
//        we will get  the connection from conext object
        ServletContext context=getServletContext();
        con=(Connection)context.getAttribute("dbcon");
        String sql = "INSERT INTO covidinfo(idate,state,total,active,deaths,userid) VALUES (now(),?,?,?,?,?)";
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
//      try
//      {
//          con.close();
//      }  
//      catch(Exception e)
//      {
//          e.printStackTrace();
//      }
    }

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out=response.getWriter();
        //reads the data
        //state=mp&userid=mpuser&total=42&active=42&death=12
//        String Userid=request.getParameter("userid");
//        String State=request.getParameter("state");
     //read the data from session object
      //fect the session objject
       HttpSession session=request.getSession();
        String Userid=(String)session.getAttribute("userid");
         String State=(String)session.getAttribute("state");
        int Total=Integer.parseInt(request.getParameter("total"));
        int Active=Integer.parseInt(request.getParameter("active"));
        int Death=Integer.parseInt(request.getParameter("death"));
        java.util.Date dt=new java.util.Date();//current date
        long val=dt.getTime();
        java.sql.Date idate=new java.sql.Date(val);

       

        
      try {

//idate,state,total,active,death,userid
    ps.setDate(1,idate);
    ps.setString(1, State);
    ps.setInt(2, Total);
    ps.setInt(3, Active);
    ps.setInt(4, Death);
    ps.setString(5,Userid);
    ps.executeUpdate();
    //provide response
    out.println("<html>");
    out.println("<body>");
    out.println("<h3>Information Submitted Succesfully</h3>");
     out.println("<h3><a href=Stateadmindashboard.jsp>Dashboard</a></h3>");
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
