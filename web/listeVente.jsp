<%@page import='model.*'%>
<%
    Vente[] ventes = (Vente[])request.getAttribute("vente");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <main id="main" class="main">
        <table>
            <tr>
                <th>Numero Vente</th>
                <th>Date Vente</th>
            </tr>
            <% for(Vente vente : ventes){%>
            <tr>
                <td><%= vente.getIdVente()%></td>
                <td><%= vente.getDates()%></td>
                <td><input type="submit" value="Voir Facture"></td>
            </tr>
            <%}%>
        </table>
        </main>
    </body>
</html>
