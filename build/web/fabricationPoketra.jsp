<%@page import="model.*"%>
<% 
PoketraMatiere[] poketraMatiere = (PoketraMatiere[]) request.getAttribute("poketraMatiere");

%>
<%@ include file="header.jsp" %>

<body>
    <%@ include file="nav.jsp" %>
    <form action="FabricationControleur" method="post">
        <h1>Creation Poketra</h1>
         <p><SELECT placeholder="Type" name="poketraMatiere">
        <% for(int i =0 ;i<  poketraMatiere.length ; i++) {%>
        <option value="<%=poketraMatiere [i].getIdPoketra()%>"><%= poketraMatiere[i].getNomMatiere()%></option>
        <%} %>
        </SELECT></p>
        <p><input type="text" name="quantite"></p>
       
        <input type="submit" value="OK">
    </form>
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