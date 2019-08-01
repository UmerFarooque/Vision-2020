<%@ page import = "dao.UserDAO" %>
<%@ page import = "domain.User" %>
<%@ page import ="java.util.*"%>
<%@ page import ="domain.Idea"%>
<%@ page import ="dao.IdeaDAO"%>
<%@ page import ="java.text.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String role = "";
            User user = new User();
            if(session.getAttribute("userIns") != null) {
                user = (User)session.getAttribute("userIns");
            }
            else 
                response.sendRedirect("index.jsp");
        %>
    <center>
        <h1 style="color:purple;">VISION 2020</h1>
        <form action="validateOperation">
            <%
                role = user.getRole();
                
            %>
            <%
                if(role.equals("admin")) {
            %>
                <input type="submit" name="operation" id="approve" value="Approve Users"/>
            <%}%>
            <%
                if(role.equals("moderator")) {
            %>
                <input type="submit" name="operation" id="myIdea" value="My Ideas"/>
            <%
                }
            %>
            <input type="submit" name="operation" id="contribute" value="Contribute"/>
            <input type="submit" name="operation" id="rating" value="Rate Idea"/>
            <input type="submit" name="operation" id="password" value="Change Password"/>
            <input type="submit" name="operation" id="logout" value="Logout"/>
        </form><br><br>
        <%
            List<Idea> ideaList = new ArrayList<Idea>();
            if(user.getRole().equals("admin")||user.getRole().equals("moderator")){
                ideaList = IdeaDAO.getAllIdea();
                %>
                <h3 style="color:mediumvioletred;">Posted Idea's</h3>
                <!--  <div id="message" style="color: green;">printing msg</div>-->
                <table border = "1" style="width: 70%;">
                    <tr>
                        <th>Id</th>
                        <th>Idea</th>
                        <th>Status</th>
                        <th>Posted Date</th>
                        <th>Approve</th>
                        <th>Reject</th>
                    </tr>
                    <%
                    int size = ideaList.size();
                    for(int i=0; i<size; i++) {
                        Idea idea = ideaList.get(i);
                    %>
                    <tr>
                    
                        <td><%= idea.getId() %></td>
                        <td><%= idea.getDescription() %></td>
                        <td><%= idea.getStatus() %></td>
                        <td><%= df.format(idea.getPostedDate()) %></td>
                        <td><a href="ideaUpdate?ideaId=<%=idea.getId()%>&status=Approved">Approve</a></td>
                        <td><a href="ideaUpdate?ideaId=<%=idea.getId()%>&status=Rejected">Reject</a></td>
                    </tr>
                    <%
                        }
                    
                    %>
                </table>
                <%
            }else{
            ideaList = IdeaDAO.getIdeaListByUser(user);
            if(ideaList.size()>0){
            %>
        <h3 style="color:mediumvioletred;">Your Idea List</h3>
        <%
        if(request.getAttribute("message") != null) { 
        %>
        <div id="message" style="color: green;"><%= request.getAttribute("message") %></div>
        <% } %>
        <table border="1" style="width: 70%;">
            <tr>
                <th>Id</th>
                <th>Idea</th>
                <th>Status</th>
                <th width="75px">Posted Date</th>
                <th>Change</th>
            </tr>
            <%
                int size = ideaList.size();
                for(int i=0; i<size; i++) {
                    Idea ide = ideaList.get(i);
                %>
                <tr>
                    <td><%= ide.getId() %></td>
                    <td><%= ide.getDescription() %></td>
                    <td><%= ide.getStatus() %></td>
                    <td><%= df.format(ide.getPostedDate()) %></td>
                    <td><a href="edit.jsp?id=<%=ide.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                
                %>
        </table>
        <%
            } else {
        %>
                <div id="empty">Contribute your idea in vision 2020</div>
        <%
            }
            }
        %>
        </center>
    </body>
</html>
