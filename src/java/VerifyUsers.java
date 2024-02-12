

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class VerifyUsers extends HttpServlet {
    private Connection con;
    private PreparedStatement ps1,ps2;
  public void init()
  {
      try
      {
          con=mypkg.Utility.connect();
          ps1=con.prepareStatement("select * from users where email=? and password=?");
          ps2=con.prepareStatement("select * from stateadmins where userid=? and password=?");
          
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
  }
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
      //VerifyUser?userid=2&pwd=edq&utype=End+User
      String id=request.getParameter("userid");
      String pw=request.getParameter("pwd");
      String utype=request.getParameter("utype");
      
    //  Cookie ck1=new Cookie("userid",id);
      
      
        System.out.println("Your Last Login Was:");
      //process the data
      if(utype.equals("Super-Admin"))
      {
          if(id.equals("sadmin")&&pw.equals("ssi"))
          {
              //1st Approach send redirect method
             // response.sendRedirect("Sadmindashboard.jsp");
              //2nd Approach  forward method of RequestDispatcher 
              RequestDispatcher rd=request.getRequestDispatcher("Sadmindashboard.jsp");
              rd.forward(request, response);
          }
          else
          {
               out.println("<html><body>");
               out.println("<h3>Invalid Super AdminAccount</h3>");
               out.println("<h3><a href=index.jsp>Try-Again</a></h3>");
               out.println("</body></html>");
          }
         
      }
      else if(utype.equals("State-Admin"))
      {
       // we will check  credentials from database ans match input give by the user
          try
          {
               //if the input giver by user is correct(id/pw)
              ps2.setString(1, id);
              ps2.setString(2, pw);
            ResultSet rs=  ps2.executeQuery();
            boolean found=rs.next();
            if(found)
            {
                 //check status
                String status=rs.getString("status");
                String uid=rs.getString("userid");
                String state=rs.getString("state");
                if(status.equals("disabled"))
                {
                    //if status is disabled the show profile updation form
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h3>Profile Updation Form</h3>");
                    out.println("<hr>");
                    out.println("<form action=UpdateStateAdminProfile>");
                    out.println("<pre>");
                    out.println("Userid   :    <input type=text name=userid value="+uid+">");
                    out.println("Password :    <input type=password name=pwd />");                   
                    out.println("Uname    :    <input type=text name=username />");
                    out.println("Email    :    <input type=text name=Email />");
                    out.println("Address  :    <input type=text name=Address />");
                    out.println("Mobile   :    <input type=text name=Mobile />");
                    out.println("<input type=submit value=Update>");
                    out.println("</pre>");
                    out.println("</form>");
                    out.println("<hr>");
                    out.println("</body>");
                    out.println("</html>");
                }
                else if(status.equals("enabled"))
                {
                     //if status is enabled the show the state admin dashboard
                    //stroing the state ans userid
                    HttpSession session=request.getSession();
                    session.setAttribute("userid",id);
                    session.setAttribute("state",rs.getString("state"));
                    response.sendRedirect("Stateadmindashboard.jsp");
                }
        
            
                
            }
            else
            {
                out.println("<html><body>");
                out.println("<h3>Invalid State Admin Account</h3>");
                out.println("<h3><a href=index.jsp>Try-Again</a></h3>");
                out.println("</body></html>"); 
            }
                
              
     
              
          }
          
      
       catch(Exception e)
       {
           
       }
      }
      else  if(utype.equals("End-User"))
      {
        //user Authentication we need to authenticate with database
        try{
            ps1.setString(1,id);
            ps1.setString(2,pw);
            ResultSet rs=  ps1.executeQuery();
            boolean found=rs.next();
        
           if(found)
           {
             response.sendRedirect("userdashboard.jsp");   
           }
           else
           {
                out.println("<html><body>");
                out.println("<h3>Invalid User Account</h3>");
                out.println("<h3><a href=Registration.jsp>Create-Account</a></h3>");
               out.println("</body></html>");
           }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
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
