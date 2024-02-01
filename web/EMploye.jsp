<%@page import="model.Personne"%>
<%
    Personne[] personnes = (Personne[]) request.getAttribute("personnes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
     <main id="main" class="main">
        <h1>Nouveau Employe</h1>
        <form action="EMployeController" method="POST" >

            <select name="personne" >
                <%
                  for(int i = 0; i < personnes.length; i++){%>
                  <option value="<%= personnes[i].getIdPersonne()%>" ><%= personnes[i].getNomPersonne()%></option>    
                <%  }  %>                
            </select>
            <input type="date" name="date" >
            <input type="submit" value="Valider">
        </form>
     </main>
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
     <%@ include file="footer.jsp" %>
    </body>
   
</html>
