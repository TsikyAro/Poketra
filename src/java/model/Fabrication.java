/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author itu1
 */
public class Fabrication {
    int idFabrication;
    int idPoketraM;
    Double quantite;

    public Fabrication(int idPoketraM, double quantite) {
        this.idPoketraM = idPoketraM;
        this.quantite = quantite;
    }
    
    public Fabrication(int idFabrication, int idPoketraM, Double quantite) {
        this.idFabrication = idFabrication;
        this.idPoketraM = idPoketraM;
        this.quantite = quantite;
    }


    public int getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(int idFabrication) {
        this.idFabrication = idFabrication;
    }

    public int getIdPoketraM() {
        return idPoketraM;
    }

    public void setIdPoketraM(int idPoketraM) {
        this.idPoketraM = idPoketraM;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Fabrication (idpoketra ,quantite ) values("+this.getIdPoketraM()+", "+this.getQuantite()+")";
        System.out.println(requete);
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            int trait = prepare.executeUpdate();
        }
    }
    
}
