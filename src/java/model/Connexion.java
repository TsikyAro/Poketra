package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	public static Connection connect() throws SQLException, ClassNotFoundException {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/poketra";
            String utilisateur = "poketra";
            String motDepasse = "poketra";
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDepasse);
            return connexion;
	}
}