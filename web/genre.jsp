<%@page import="model.Poketra"%>
<%@page import="model.LookMatiere"%>
<%
    Poketra[] poketra= (Poketra[])request.getAttribute("poketra");
    //Look[] listlook = (Look[])request.getAttribute("listlook");
    
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
    <form action="TrieControleur" method="post">
        <h1>Voir liste matiere selon les Look</h1>
        <p><select aria-placeholder="TypeLook" name="look">
          <% for(int j=0 ; j< poketra.length ; j++) {%>
          <option value="<%= poketra[j].getIdPoketra() %>"><%= poketra[j].getNomType() %></option>
         <%  } %>
        </select></p>
        <input type="submit" value="Envoyer">
    </form>
    
    <div id="div">
        <table>
            <thead>
                <th>Homme</th>
                <th>Femme</th>
            </thead>
            <tbody>
                <%  if(lm!=null){
                    for(int i=0 ; i< lm.length ; i++){%>
                <tr>
                    <td><%= lm[i].getNomMatiere() %></td>
                    <td><%= lm[i].getNomLook() %></td>
                </tr>
                <% }
                } %>
                <!-- Ajoutez d'autres lignes au besoin -->
            </tbody>
        </table>
    </div>
</body>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        /* display: flex; */
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    form {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-bottom: 20px;
    }

    select {
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
