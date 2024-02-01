
<%@page import="model.Personne"%>
<%@page import="model.*"%>
<%
    Personne[] personnes = (Personne[]) request.getAttribute("personnes");
    Poketra[] poketra = (Poketra[]) request.getAttribute("poketra");
    Fabrication[] fabrication = (Fabrication[]) request.getAttribute("fabrication");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
  <main id="main" class="main">
        <h1>Poketra Maker</h1>
        <form action="PoketraMakerControlleur" method="POST" >
            <select name="poketra" >
                <%
                  for(int i = 0; i < poketra.length; i++){%>
                  <option value="<%= poketra[i].getIdPoketra()%>" ><%= poketra[i].getNomType()%></option>    
                <%  }  %>
            </select>
            <input type="text" name="duree" placeholder="Duree" >
            <select name="personne" >
                <%
                  for(int i = 0; i < personnes.length; i++){%>
                  <option value="<%= personnes[i].getIdPersonne()%>" ><%= personnes[i].getNomPersonne()%></option>    
                <%  }  %>                
            </select>
            <select name="fabrication" >
                <%
                  for(int i = 0; i < fabrication.length; i++){%>
                  <option value="<%= fabrication[i].getIdFabrication()%>" >Fabrication000<%= fabrication[i].getIdFabrication()%></option>    
                <%  }  %>                
            </select>
            <input type="submit" value="Valider">
        </form>
  </main>
             <%@ include file="footer.jsp" %>
    </body>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    form {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    Select {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #4caf50;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>
   
</html>
