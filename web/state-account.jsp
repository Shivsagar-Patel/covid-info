<html>
    
    <body>
         <h3>State Account Creation Form</h3>
        <hr>
       
     <form action="StateAccountCreationServlet">
     <!--<form action=" dregistrationservlet">-->
             <table>
                 <tr>
                      <td>User-Id</td><td><input type="text" name="uid" /></td>
                 </tr>
                 <tr>
                       <td>Password</td><td><input type="password" name="password" /></td>
                 </tr> 
                 <tr>
                             <td>State</td><td><select name="state" >
                                     <option>MP</option>
                                     <option>MH</option>
                                     <option>RJ</option>
                                     <option>UP</option>
                                     <option>GJ</option>
                         </select></td> 
                 </tr>
                
                  <tr>     <td><input type="submit" value="Create-Account"/></td><td><input type="reset" value="Reset"/></td></tr>
           </table>                 
        </form>
       
        <hr>
    </body>
   
</html>

