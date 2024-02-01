<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
  <main id="main" class="main">
    <h1>Insertion Type</h1>
    <form action="TypeController" method="post">
        <p><input type="text" aria-placeholder="NomMatiere" name="nomType"></p>
        <input type="submit" value="Envoyer">
    </form>
  </main>
</body>
<%@ include file="footer.jsp" %>
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

    input[type="text"] {
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
