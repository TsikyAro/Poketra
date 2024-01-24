<%@page import="model.*"%>
<%
    Poste[] poste = (Poste[])request.getAttribute("postes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SalairePosteControlleur" method="post">
            <select name="idPoste">
                <% for (int i=0; i<poste.length;i++){%>
                <option value="<%=poste[i].getIdPoste()%>"><%= poste[i].getNomPoste()%></option>>
                <%}%>
            </select>
            <input type="text" name="salaire" >
            <input type="date" name="dates" >
            <input type="submit" value="valider" >
        </form>
    </body>
</html>
