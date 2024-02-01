<%@page import="model.Taille"%>
<%@page import="model.TypePoketra"%>
<%@page import="model.Matiere"%>
<%@page import="model.*"%>
<%
    Poketra poketra = (Poketra) request.getAttribute("poketra");
    Matiere[] matieres = (Matiere[]) request.getAttribute("matiere");
    PoketraMatiere [] poketraM = (PoketraMatiere[]) request.getAttribute("poketraM");
%>
<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
  <main id="main" class="main">
    <form action="PoketraMatiereController" method="post">
        <h1>Creation Poketra : <%=poketra.getNomType()%> de taille <%= poketra.getNomTaille()%></h1>
        <input type="hidden" value="<%= poketra.getIdPoketra()%>" name="poketra">
         <p><SELECT placeholder="matiere" name="matiere">
        <% for(int i =0 ;i<  matieres .length ; i++) {%>
        <option value="<%=matieres [i].getIdMatiere()%>"><%= matieres[i].getNomMatiere()%></option>
        <%} %>
        </SELECT></p>
        <input type="text" placeholder="Quantite" name="quantite">
        <input type="submit" value="Ajouter">
    </form>
        <div id="div">
        <table>
            <thead>
                <th>Matiere</th>
                <th>Quantite</th>
            </thead>
            <tbody>
                <%  if(poketraM!=null){
                    for(int i=0 ; i< poketraM.length ; i++){%>
                <tr>
                    <td><%= poketraM[i].getNomMatiere() %></td>
                    <td><%= poketraM[i].getQuantite() %></td>
                </tr>
                <% }
                } %>
            </tbody>
        </table>
    </div>
            <a href="index.jsp" >Terminer</a>
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
    #div {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #4caf50;
        color: white;
    }
</style>

</html>