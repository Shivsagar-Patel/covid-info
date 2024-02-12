<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        response.sendRedirect("index.jsp");
    }
    
%>
<html>
   
    <body>
        <h3>State-Admin Dashboard</h3>
         <hr>
         <pre>
         <a href="entrydata.jsp">Add-Information-For-Today</a>
         <a href="">Update-Information</a>
         <a href="StateDataServlet">View-Information-For-Own-State</a>
         <a href="AllStateData">View-Information-For-All-States</a>
         <a href="EndSession">Logout</a>
        </pre>
         <hr>
    </body>
</html>