<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erreur de conversion</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }

        .error-container {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #ff0000;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Erreur de donnes</h1>
        <p>La valeur que vous avez entrer n'est plus disponible en Stock. Veuillez entrer une valeur plus petit.</p>
        <p><a href="javascript:history.back()">Retour</a></p>
    </div>
</body>
</html>
