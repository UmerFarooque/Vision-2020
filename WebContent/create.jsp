<%@ page import = "dao.CategoryDAO" %>
<%@ page import = "domain.Category" %>
<%@ page import = "java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Idea Creation</title>
    </head>
    <body>
        <center>
            <h1 style="color:purple;">VISION 2020</h1>
            <h3><a href="display.jsp">Home</a></h3>
            <h3 style="color:mediumvioletred;">Create your idea</h3>
        <%
        	if(request.getAttribute("error") != null) {
        %>
        <div id="error" style="color: red;"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="validateIdea">
            
            <table>
                <tr>
                    <td>Give your idea</td><td><textarea rows="4" cols="50" name="idea" id="idea"></textarea></td>
                </tr>
                <tr>
                    <td>Select Category</td>
                        <td>
                            <select name="categoryId" id="categoryId">
                                <%
                                    List<Category> list = CategoryDAO.getAllCategory();
                                    for(Category cat: list) {
                                        int id = cat.getId();
                                        String catName = cat.getName();
                                %>
                                    <option value="<%=id%>"><%=catName%></option>
                                <% } %>
                            </select>
                        </td>
                </tr>
                <tr style="text-align:center;">
                    <td colspan="2">
                        <input type="submit" name="createOrUpdate" id="create" value="Create">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>
