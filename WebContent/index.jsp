
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <h1 style="color:purple;">VISION 2020</h1>
        <h3 style="color:mediumvioletred;">Login</h3> 
        <%
            if(request.getAttribute("error") != null) {
        %>
        <div id="error" style="color: red;"><%= request.getAttribute("error") %></div>
        <%
            }
        %>
        <%
            if(request.getAttribute("message") != null) {
        %>
        <div id="message" style="color: green;"><%= request.getAttribute("message") %></div>
        <%
            }
        %>
        
        <form action="loginValidation" method="GET">
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
                    <td></td>
                    <td>
                        <input type="submit" name="action" id="login" value="Login">
                        <input type="submit" name="action" id="register" value="Register">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>
