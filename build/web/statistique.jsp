<%@page import="java.util.Arrays"%>
<%@page import="model.Poketra"%>
<%
    Poketra[] poketra= (Poketra[])request.getAttribute("poketra");
    Double[] stat = (Double[]) request.getAttribute("produit");
    //Look[] listlook = (Look[])request.getAttribute("listlook");
    
 %><%@include file="header.jsp"%>
 <script src="charte.js"></script>
<body>
  <%@ include file="nav.jsp" %>
<main id="main" class="main">

<canvas id="myChart" style="width:100%;max-width:600px"></canvas>
<script>
var xValues = ["Femme", "Homme"];
var yValues = <%= Arrays.toString(stat) %>;
var barColors = [
  "#b91d47",
  "#00aba9"
];

new Chart("myChart", {
  type: "pie",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  },
  options: {
    title: {
      display: true
    }
  }
});
</script>




    <form action="StatistiqueController" method="post">
        <h1>Voir liste matiere selon les Look</h1>
        <p><select aria-placeholder="TypeLook" name="idproduit">
          <% for(int j=0 ; j< poketra.length ; j++) {%>
          <option value="<%= poketra[j].getIdPoketra() %>"><%= poketra[j].getNomType() %></option>
         <%  } %>
        </select></p>
        <input type="submit" value="Envoyer">
    </form>
    
    <div id="div">
        <table>
            <thead>
                <th>Femme</th>
                <th>Homme</th>
            </thead>
            <tbody>
                <tr>
                    <td><%= stat[0] %></td>
                    <td><%= stat[1]%></td>
                </tr>
                <!-- Ajoutez d'autres lignes au besoin -->
            </tbody>
        </table>
    </div>

</body>
</main>
<%@ include file="footer.jsp" %>
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