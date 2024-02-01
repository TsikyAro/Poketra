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
public class PrixDate {
    int prixDate;
    int idMatiere;
    Double prix;
    Date dates;

    public PrixDate(int prixDate, int idMatiere, Double prix, Date dates) {
        this.prixDate = prixDate;
        this.idMatiere = idMatiere;
        this.prix = prix;
        this.dates = dates;
    }

    public PrixDate(int idMatiere, double prix, Date date) {
        this.idMatiere = idMatiere;
        this.prix = prix;
        this.dates = date;
    }

    public int getPrixDate() {
        return prixDate;
    }

    public void setPrixDate(int prixDate) {
        this.prixDate = prixDate;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into prix_matiere(idMatiere, prix, date) values(?, ?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdMatiere());
            prepare.setDouble(2, this.getPrix());
            prepare.setDate(3, this.getDates());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }

    public PrixDate[] select(Connection connexion) throws SQLException{
        String requete = "select * from prix_matiere";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PrixDate> array = new ArrayList<PrixDate>();
        while(result.next()){
            int prixD = result.getInt("prixDate");
            int idMat = result.getInt("idMatiere");
            Double prixx = result.getDouble("prix");
            Date d = result.getDate("dates");
            PrixDate rep = new PrixDate(prixD, idMat, prixx, d);
            array.add(rep);
        }
        connexion.close();
        return array.toArray(new PrixDate[array.size()]);
    }
    
}
