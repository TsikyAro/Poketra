<%@page import="model.PersonnePoste"%>
<%@page import="model.Personne"%>
<%@page import="model.Poste"%>

<% 

Personne[] personne = (Personne[])request.getAttribute("personne");
Poste[] poste = (Poste[])request.getAttribute("poste");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="PersonnePosteControleur" method="post">
        <h1>Insertion Personne Poste</h1>
        <p><SELECT placeholder="Nom" name="personne">
           <% for(int i =0 ;i<  personne.length ; i++) {%>
            <option value="<%= personne[i].getIdPersonne() %>"><%= personne[i].getNomPersonne() %></option>
            <%} %>
        </SELECT></p>
        <p><SELECT placeholder="Poste" name="poste">
        <% for(int i =0 ;i<  poste .length ; i++) {%>
        <option value="<%=poste[i].getIdPoste() %>"><%= poste[i].getNomPoste() %></option>
        <%} %>
        </SELECT></p>
        <p><input type="date" name="debut"></p>
        <input type="submit" value="OK">
    </form>

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