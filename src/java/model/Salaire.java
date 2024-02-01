package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Salaire {
    double Salaire;
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into salaire(salaire ) values(?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setDouble(1, this.getSalaire());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
    public Salaire(double Salaire) {
        this.Salaire = Salaire;
    }

    public double getSalaire() {
        return Salaire;
    }

    public void setSalaire(double Salaire) {
        this.Salaire = Salaire;
    }
    
}
