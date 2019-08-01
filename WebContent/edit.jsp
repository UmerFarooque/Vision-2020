<%@ page import = "dao.IdeaDAO" %>
<%@ page import = "domain.Idea" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Idea</title>
    </head>
    <body>
        <center>
            <h1 style="color:purple;">VISION 2020</h1>
            <h3><a href="display.jsp">Home</a></h3>
            <h3 style="color:mediumvioletred;">Change your idea</h3>
        <%
           String error=(String)request.getAttribute("error");
            if(error==null){
                error="";
            }
            int id = Integer.parseInt(request.getParameter("id"));
            Idea idea = IdeaDAO.getIdeaById(id);
        %>
        <div id="error" style="color: red;"><%=error%></div>
        <form action="validateIdea">
            <table>
                <tr>
                    <td>Give your idea</td><td><textarea rows="4" cols="50" name="idea"><%=idea.getDescription()%></textarea></td>
                </tr>
                <tr>
                    <td>Status</td><td><%= idea.getStatus() %></td>
                </tr>
                <tr>
                    <td>Posted Date</td><td><%= idea.getPostedDate() %></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="ideaId" id="ideaId" value="<%=idea.getId()%>"/>
                        <input type="submit" name="createOrUpdate" id="update" value="Update">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>
