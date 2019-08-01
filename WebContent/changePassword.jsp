<%@ page import="domain.User" %>

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
            <h3 style="color:mediumvioletred;">Change Password</h3>
        <%
        if(request.getAttribute("error") != null) {
        %>
        <div id="error" style="color: red;"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="updatePassword">
            <table>
                <tr>
                    <td>Password</td><td><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td>New Password</td><td><input type="text" name="newPassword"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="changepwd" id="changePassword" value="Update Password">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>
