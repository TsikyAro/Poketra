<%@page import="model.*"%>
<%
    V_reste_poketra[] restes = (V_reste_poketra[])request.getAttribute("reste");
%>
<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
 <main id="main" class="main">
    <div class="card">
    <div class="card-body">
      <h5 class="card-title">Liste Reste Poketra en Stock</h5>
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">Nom Type</th>
            <th scope="col">Taille</th>
            <th scope="col">Quantite</th>
          </tr>
        </thead>
        <tbody>
            <%for(V_reste_poketra reste: restes){%>
                <tr>
                  <td><%= reste.getNomType()%></td>
                  <td><%= reste.getNomTaille()%></td>
                  <td><%= reste.getValeur()%></td>
                </tr>
            <%}%>
        </tbody>
      </table>
    </div>
    </div>
 </main>
 <%@ include file="footer.jsp" %>
</body>

