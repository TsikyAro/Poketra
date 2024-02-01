<%@page import="model.Look"%>
<%@page import="model.LookMatiere"%>
<%
    LookMatiere[] lm= (LookMatiere[])request.getAttribute("lm");
    Look[] listlook = (Look[])request.getAttribute("listlook");
    
 %>
 <%@ include file="header.jsp" %>
<body>
    <%@ include file="nav.jsp" %>
    <main id="main" class="main">
    <% if(request.getAttribute("erreur")!=null){%>
        <%
          String erreur = (String)request.getAttribute("erreur");  
        %>
        <%= erreur%>
    <%}%>
    <form action="TrieControleur" method="post">
        <h1>Voir liste matiere selon les Look</h1>
        <p><select aria-placeholder="TypeLook" name="look">
          <% for(int j=0 ; j< listlook.length ; j++) {%>
          <option value="<%= listlook[j].getNomLook() %>"><%= listlook[j].getNomLook() %></option>
         <%  } %>
        </select></p>
        <input type="submit" value="Envoyer">
    </form>
    
    <div id="div">
        <table>
            <thead>
                <th>Matiere</th>
                <th>Look</th>
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
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>
