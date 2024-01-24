<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="PosteControlleur" method="GET" >
            <input placeholder="nouveau Poste "typr="text" name="nomPoste" >
            <input placeholder="hierarchie" type="number" name="hierarchie" >
            <input type="submit" value="valider" >
        </form>
    </body>
</html>
