<%@taglib uri="/WEB-INF/tlds/mylib.tld" prefix="data" %>
<html>
    <body bgcolor="lightgreen">
        
          <h3>Covid-Information-Portal</h3>
          <data:totalcases/>
        <hr>
       
         <form action="VerifyUsers" method="get" >
           
           <pre>
            Email/User-Id  <input type="text" name="userid" /> <br>
            Password       <input type="password" name="pwd" /><br>
            User-Type      <select name="utype"><option>End-User</option><option>State-Admin</option><option>Super-Admin</option></select><br>
                           <input type="submit" value="Login"/> <input type="reset" value="Reset"/>
           </pre>                 
        </form>
       
        <hr>
        <a href="Registration.jsp">New-User-Registration</a><br>
        <a href="helpline.jsp">Covid-Helpline-No</a>
        <br>
        <!--<a href="New">mmmm</a>-->
    </body>
</html>
<%@include file="info.jsp" %>
