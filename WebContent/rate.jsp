<%@ page import="domain.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>



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
           
            <%
                User user = (User)session.getAttribute("userIns");
                List<Idea> ideaList=new ArrayList<Idea>();
                ideaList = IdeaDAO.getAllIdea();
                List<UserIdeaRating> userIdeaRatingsList=new ArrayList<UserIdeaRating>();
                userIdeaRatingsList = UserIdeaRatingDAO.getRatingByUserId(user.getId());
                int size = userIdeaRatingsList.size();
                List<Rating> ratingList = new ArrayList<Rating>();
                ratingList = RatingDAO.getAllRating();
		

                String msg=(String)request.getAttribute("message");
                if(msg==null){
                    msg="";
                }
                    if(userIdeaRatingsList.size()>0){
            %>
            <h3 style="color:mediumvioletred;">The idea's which you rated</h3>
            <div id="message" style="color: green;"><%=msg%></div>
            <table border="1" style="width: 70%;">
                <tr>
                    <th>Id</th>
                    <th>Idea</th>
                    <th>Rating</th>
                    <th>Created By</th>
                    <th>Rated Date</th>
                </tr>
                <%
                    int i=1;
                    for(UserIdeaRating userIdeaRatingIns:userIdeaRatingsList){
                        %>
                        <tr>
                            <td><%=i%></td>
                            <td><%=userIdeaRatingIns.getIdea().getDescription()%></td>
                            <td><%=userIdeaRatingIns.getRating().getName()%></td>
                            <td><%=userIdeaRatingIns.getIdea().getPostedBy().getName()%></td>
                            <td><%=userIdeaRatingIns.getRatedDate()%></td>
                        </tr>
                        <%
                        i++;
                    }
                %>
            </table><br><br>
            <%
                    }
                    if(ideaList.size()>0){
            %>
            <h3 style="color:mediumvioletred;">Give rating for ideas</h3>
            <form action="validateRating">
                <div style="width:70%;">
                    <div style="">
                    Select Idea    
                    <select style="width: 30%;" name="ideaId">
                        <%
                            for(Idea i: ideaList) {
                        %>
                            <option value="<%=i.getId()%>"><%= i.getId() %></option>
                        <% } %>
                    </select>
                    </div><br>
                    <div style="margin-right: 15%;">
                        Select Rating
                        <select name="ratingId">
                            <%
                                for(Rating r: ratingList) {
                            %>
                                <option value="<%=r.getId()%>"><%= r.getId() %></option>
                            <% } %>
                        </select>
                    </div><br>  
                            <div style="margin-right: 10%;">
                        <input type="submit" name="rate" value="Give Rating"/>
                    </div>
                </div>
            </form>
           <%
                }
           %>
         </center>
    </body>
</html>
