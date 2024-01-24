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
public class Matiere {
    int idMatiere;
    String nomMatiere;

    public Matiere(int idMatiere, String nomMatiere) {
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
    }

    public Matiere() {
     
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into matiere(nommatiere) values(?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomMatiere());
            int trait = prepare.executeUpdate();
        }
    }
    
    public Matiere[] select(Connection connexion) throws SQLException{
        String requete = "select * from matiere";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Matiere> array = new ArrayList<Matiere>();
        while(result.next()){
            int id = result.getInt("idMatiere");
            String nom = result.getString("nomMatiere");
            Matiere m = new Matiere(id, nom);
            array.add(m);
        }
        return array.toArray(new Matiere[array.size()]);
    }
}
