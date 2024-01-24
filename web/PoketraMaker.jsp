<%-- 
    Document   : PoketraControlleur
    Created on : 16 janv. 2024, 14:52:44
    Author     : itu1
--%>
<%@page import="model.Personne"%>
<%@page import="model.Poketra"%>
<%
    Personne[] personnes = (Personne[]) request.getAttribute("personnes");
    Poketra[] poketra = (Poketra[]) request.getAttribute("poketra");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
        <h1>Poketra Maker</h1>
        <form action="PoketraMakerControlleur" method="POST" >
            <select name="poketra" >
                <%
                  for(int i = 0; i < poketra.length; i++){%>
                  <option value="<%= poketra[i].getIdPoketra()%>" ><%= poketra[i].getNomType()%></option>    
                <%  }  %>
            </select>
            <input type="text" name="duree" >
            <select name="personne" >
                <%
                  for(int i = 0; i < personnes.length; i++){%>
                  <option value="<%= personnes[i].getIdPersonne()%>" ><%= personnes[i].getNomPersonne()%></option>    
                <%  }  %>                
            </select>
            <input type="submit" value="Valider">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
