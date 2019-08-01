<%@ page import = "domain.User" %>
<%@ page import = "dao.UserDAO" %>
<%@ page import = "java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1 style="color:purple;">VISION 2020</h1>
        <h3><a href="display.jsp">Home</a></h3>
        <h3 style="color:mediumvioletred;">Vision 2020 users</h3>
        <%
            List<User> userList=new ArrayList<User>();
            userList = UserDAO.getAllUsers();
            int size = userList.size();
            String msg=(String)request.getAttribute("message");
            if(msg==null){
                msg="";
            }
	    if(size>0){
        %>
        <div id="message" style="color: green;"><%=msg%></div>
        <table border="1" style="width: 70%;">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Age</th>
                <th>Status</th>
                <th>Approve</th>
                <th>Block</th>
            </tr>
            <%
            for(int i=0; i<size; i++) {
                if(userList.get(i).getRole().equals("admin")) continue;
                else {
                    User u = userList.get(i);
                %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getName() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getAge() %></td>
                    <td><%= u.getStatus() %></td>
                    <td><a href="userUpdate?status=Approved&userId=<%=u.getId()%>">approve</a></td>
                    <td><a href="userUpdate?status=Blocked&userId=<%=u.getId()%>">block</a></td>
                </tr>
                <%
                }
            }
            %>
        </table>
        <%
        }else{
        %>
        <div id="empty">No users in vision 2020</div>
        <%
            }
        %>
    </center>
    </body>
</html>
