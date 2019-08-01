<%@ page import = "domain.Idea" %>
<%@ page import = "dao.IdeaDAO" %>
<%@ page import = "java.util.*" %>
<%@ page import = "domain.User" %>




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
        <h3 style="color:mediumvioletred;">Your Idea's</h3>
        <%           
            List<Idea> ideaList=new ArrayList<Idea>();
            ideaList = IdeaDAO.getIdeaListByUser((User)session.getAttribute("userIns"));
            String msg=(String)request.getAttribute("message");
            if(msg==null){
                msg="";
            }
            if(ideaList.size()>0){
        
        %>
        <div id="message" style="color: green;"><%=msg%></div>
        <table border="1" style="width: 70%;">
            <tr>
                <th>Id</th>
                <th>Idea</th>
                <th>Status</th>
                <th>Posted Date</th>
                <th>Change</th>
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
                    <td><%= idea.getPostedDate() %></td>
                    <td><a href="edit.jsp?id=<%=idea.getId()%>">change</a></td>
                </tr>
                <%
                }
            %>
        </table><br>
        <%
            } else {
        %>
        <div id="empty">Contribute your idea in vision 2020</div>
        <%
            }
        %>
    </center>
    </body>
</html>
