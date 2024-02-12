<% 
      int x=10; 
    String state=request.getParameter("t1");
    String contact="";
    switch(state)
    {
        case "mp":
            contact="18003242334";
            break;
        case "mh":
            contact="19003242334";
            break;
        case "rj":
            contact="14003242334";
            break;
        case "gj":
            contact="15003242334";
            break;
        default :
            contact="123456789";
            break;
    }
       
%>
<html>
   
    <body>
        <h3>Helpine Numbers For Your States :<% out.println(state);%></h3>
        Numbers   :                          <%out.println(contact);%>
        <a href="index.jsp">Home</a>
    </body>
    
</html>

