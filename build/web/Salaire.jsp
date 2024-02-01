<%@include file="header.jsp"%>
<body>
  <%@ include file="nav.jsp" %>
         <main id="main" class="main">
        <form action="SalaireController" method="post">
            <label>Salaire</label>
            <input type="text" name="salaire">
            <input type="submit" value="Valider">
        </form>
         </main>    
          <%@ include file="footer.jsp" %>
    </body>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: row;
        }

        .main-menu {
            background-color: #333;
            width: 200px;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .main-menu ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .main-menu li {
            margin-bottom: 10px;
        }

        .main-menu a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
            font-size: 16px;
        }

        .main-menu a:hover {
            color: #4caf50;
        }

        .content {
            padding: 20px;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            width: 300px; /* Adjust width as needed */
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
