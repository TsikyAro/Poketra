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
public class Taille {
    int idTaille;
    String nomTaille;

    public Taille(int idTaille, String nomTaille) {
        this.idTaille = idTaille;
        this.nomTaille = nomTaille;
    }

    public Taille() {
     
    }

    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
    }

    public String getNomTaille() {
        return nomTaille;
    }

    public void setNomTaille(String nomTaille) {
        this.nomTaille = nomTaille;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Taille(nomTaille) values(?)";
        
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomTaille());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
    
    public Taille[] select(Connection connexion) throws SQLException{
        String requete = "select * from Taille";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Taille> array = new ArrayList<>();
        while(result.next()){
            int id = result.getInt("idTaille");
            String nom = result.getString("nomTaille");
            Taille m = new Taille(id, nom);
            array.add(m);
        }
        connexion.close();
        return array.toArray(new Taille[array.size()]);
    }
}

