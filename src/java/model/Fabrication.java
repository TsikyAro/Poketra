/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author itu1
 */
public class Fabrication {
    int idFabrication;
    int idPoketraM;
    Double quantite;
    Date dateFabrication;

    public Fabrication() {
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

//    public Fabrication(int idPoketraM, Double quantite) {
//        this.idPoketraM = idPoketraM;
//        this.quantite = quantite;
//    }

    public Fabrication(int idFabrication, int idPoketraM, Double quantite, Date dateFabrication) {
        this.idFabrication = idFabrication;
        this.idPoketraM = idPoketraM;
        this.quantite = quantite;
        this.dateFabrication = dateFabrication;
    }
    
    
    
    public Fabrication(int idPoketraM, double quantite,Date fabrication) {
        this.idPoketraM = idPoketraM;
        this.quantite = quantite;
        this.dateFabrication = fabrication;
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
        String requete = "insert into Fabrication (idpoketra ,quantite,dateFabrication ) values("+this.getIdPoketraM()+", "+this.getQuantite()+",'"+this.getDateFabrication()+"')";
        System.out.println(requete);
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
     public Fabrication[] select(Connection connexion) throws SQLException{
        String requete = "select * from Fabrication";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Fabrication> array = new ArrayList<Fabrication>();
        while(result.next()){
            Fabrication type = new Fabrication(result.getInt("idfabrication"), result.getInt("idPoketra"), result.getDouble("quantite"), result.getDate("datefabrication"));
            array.add(type);
        }
        connexion.close();
        return array.toArray(new Fabrication[array.size()]);
    } 
    
}
