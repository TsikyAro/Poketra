/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author itu1
 */
public class Duree {
    int idDuree;
    int idPoketra;
    double duree;

    public Duree(int idDuree, int idPoketra, double duree) {
        this.idDuree = idDuree;
        this.idPoketra = idPoketra;
        this.duree = duree;
    }

    public int getIdDuree() {
        return idDuree;
    }

    public void setIdDuree(int idDuree) {
        this.idDuree = idDuree;
    }

    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Duree (idDuree , idPoketra, duree) values(?, ?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdDuree());
            prepare.setInt(2, this.getIdPoketra());
            prepare.setDouble(3, this.getDuree());
            int trait = prepare.executeUpdate();
        }
    }
    
    public Duree[] select(Connection connexion) throws SQLException{
        String requete = "select * from Duree";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Duree> array = new ArrayList<Duree>();
        while(result.next()){
            int id = result.getInt("idDuree");
            int poketra = result.getInt("idPoketra");
            double valeur = result.getDouble("duree");
            Duree duree = new Duree(id, poketra, valeur);
            array.add(duree);
        }
        return array.toArray(new Duree[array.size()]);
    }
    
}
