<%-- 
    Document   : poubatal
    Created on : 17 mars 2013, 18:02:19
    Author     : sildar
--%>

<%@page import="fr.univnantes.atal.poubatal.opendata.CollectePoint"%>
<%@page import="fr.univnantes.atal.poubatal.opendata.DataManager"%>
<%@page import="fr.univnantes.atal.poubatal.datastore.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/REC-html40/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Collectable days by street</h1>
        <%
        User user = new User("foobar");
        user.save();
        user = User.load("foobar");
        %>
        <%=user.getId()%><br/>
        <%
        
        for (CollectePoint point : DataManager.getInstance().getPoints())
        {
        %>
        <%=point.toString()%>
    <br/>
        
        <%
         }
        %>
    </body>
</html>
