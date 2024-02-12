<%@page import="java.sql.*,mypkg.Utility" %>
<%@taglib uri="/WEB-INF/tlds/mylib.tld" prefix="data" %>

<%!
    int x=20;
    public int deathPercentage(int totaldeaths,int totalcases)
{
    int dp=(totaldeaths*100)/totalcases;
    return dp;
}

%>
<%
    int x=10;
    String sql="select * from covidinfo";
    Connection con=Utility.connect();
    PreparedStatement ps=con.prepareStatement(sql);
    ResultSet rs=ps.executeQuery();
%>
<html>
    <body>
        <h1>Covid Data</h1>
        <table border="2">
            <tr>
                <th>Sno</th><th>State</th><th>Date</th><th>Total</th><th>Active</th><th>Deaths</th><th>Death%</th>
            </tr>
            <%
                while(rs.next())
                {
                    String s1=rs.getString(1);
                    String s2=rs.getString(2);
                    String s3=rs.getString(3);
                    int s4=rs.getInt(4);
                    String s5=rs.getString(5);
                    int s6=rs.getInt(6);
                   int per=deathPercentage(s4,s6);
                
            %>
           
            <tr>
                <td><%=s1 %></td>
                <td><%=s2%></td>
                <td><%=s3%></td>
                <td><%=s4%></td>
                <td><%=s5%></td>
                <td><%=s6%></td>
                <td><%=per%></td>
                
            </tr>
            <%
                }
            %>
            <%=x%>
        </table>
        <data:totalcases/>
        <a href="userdashboard.jsp">userdashboard</a>
    </body>
</html>
