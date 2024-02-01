<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>

<% 
Poketra[] poketra = (Poketra[])request.getAttribute("poketra");
Client[] client = (Client[])request.getAttribute("client");
%>
<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
<main id="main" class="main">
     <form action="VenteControleur" method="post">
        <label for="productName">Nom du produit:</label>
         <p><SELECT placeholder="produit" name="produit" id="produit">
           <% for(int i =0 ;i<  poketra.length ; i++) {%>
            <option value="<%= poketra[i].getIdPoketra()%>;<%= poketra[i].getNomType() %> "><%= poketra[i].getNomType() %></option>
            <%} %>
        </SELECT></p>
       
        <label for="quantity">Quantité:</label>
        <input type="number" id="quantity" required name="quantite">

        <button type="submit">Ajouter au panier</button>
    </form>
     <% if(request.getAttribute("paniers")!=null){
            ArrayList<Panier> paniers = (ArrayList<Panier>) request.getAttribute("paniers");
        %>
        <table>
            <tr>
             <th>Nom Produit</th>
             <th>Quantite</th>
            </tr>
            <% for(Panier panier : paniers){%>
            <tr>
                <td><%= panier.getNomPoketra()%></td>                
                <td><%= panier.getQuantite()%></td>
            </tr>
            <%}%>
            
        </table>
        <form action="ValiderController" method="post">
            <p><SELECT placeholder="Client" name="client" id="client">
               <% for(int i =0 ;i<  client.length ; i++) {%>
                <option value="<%= client[i].getIdClient() %>"><%= client[i].getNomClient() %></option>
                <%} %>
            </SELECT></p>

            <label for="Date">Date:</label>
            <input type="date" id="date"  name="date">
            <input type="submit" value="Valider">
        </form>
            
    <%}%> 
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