
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
        <h3 style="color:mediumvioletred;">Register</h3>
        <%
            if(request.getAttribute("error") != null) {
        %>
        <div id="error" style="color: red;"><%= request.getAttribute("error") %></div>
        <%
            }
        %>
        <form action="validateUser" method="GET">
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password"/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email"/></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td><input type="text" name="age"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="action" id="register" value="Register">
                        <input type="submit" name="action" id="cancel" value="Cancel">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>
