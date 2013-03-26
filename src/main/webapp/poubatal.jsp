<%-- 
    Document   : poubatal
    Created on : 17 mars 2013, 18:02:19
    Author     : sildar
--%>

<%@page import="fr.univnantes.atal.poubatal.opendata.CollectePoint"%>
<%@page import="fr.univnantes.atal.poubatal.opendata.DataManager"%>
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
        
        for (CollectePoint point : DataManager.getInstance().getPoints())
        {
        %>
        <%=point.toString()%>
    <br/>
        
        <%
         }
        %>
        
	<script type="text/javascript">
		function load() {
			console.log('Google API loading...');
			gapi.client.load('oauth2', 'v2', authorize);
		}
			
		function authorize(event) {
			// Step 3: get authorization to use private data
			gapi.auth.authorize({
				client_id: '385110343883.apps.googleusercontent.com', 
				scope: [
				'https://www.googleapis.com/auth/userinfo.email',
				'https://www.googleapis.com/auth/userinfo.profile'
				], 
				immediate: false
			}, handle_token);
			return false;
		}

		function handle_token(auth_result) {
			console.log(auth_result)
			if (auth_result && !auth_result.error) {
				gapi.auth.setToken(auth_result);
				var request = gapi.client.oauth2.userinfo.get();
				request.execute(handle_email);
			} else {
			}
		}

		function handle_email(email_result) {
			console.log(email_result);
		}
	</script>
	<script type="text/javascript" src="https://apis.google.com/js/client.js?onload=load"></script>
    </body>
</html>
