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
 * @author Tsiky Aro
 */
public class EtatdeStock {
    double valeur ;
    int IdMatiere;
    String nomMatiere;
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Fabrication (idPoketraMatiere, Quantite) values(?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setDouble(1, this.getValeur());
            prepare.setInt(2, this.getIdMatiere());
            int trait = prepare.executeUpdate();
        }
    }
    
    public EtatdeStock etatParMatiere(Connection connexion,int idmatiere) throws SQLException{
        String requete = "select * from etat_de_stock where idMatiere ="+idmatiere;
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        if(result.next()){
            EtatdeStock etat = new EtatdeStock(result.getDouble(1), result.getInt(2));
            return etat;
        }
        return new EtatdeStock();
    }
    public EtatdeStock[] etatParStockparMatiere(Connection connexion,int idmatiere) throws SQLException{
        String requete = "select * from affichageEtat_de_stock where idMatiere ="+idmatiere;
          ArrayList<EtatdeStock> liste = new ArrayList<>();
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        if(result.next()){
            EtatdeStock etat = new EtatdeStock(result.getDouble(1), result.getInt(2),result.getString(3));
            liste.add(etat);
        }
        return liste.toArray(new EtatdeStock[liste.size()]);
    }
    public EtatdeStock [] etatParStock(Connection connexion) throws SQLException{
        String requete = "select * from affichageEtat_de_stock ";
        ArrayList<EtatdeStock> liste = new ArrayList<>();
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        while(result.next()){
            EtatdeStock etat = new EtatdeStock(result.getDouble(1), result.getInt(2),result.getString(3));
            liste.add(etat);
        }
        return liste.toArray(new EtatdeStock[liste.size()]);
    }
    
    public EtatdeStock() {
    }

    public EtatdeStock(double valeur, int IdMatiere, String nomMatiere) {
        this.valeur = valeur;
        this.IdMatiere = IdMatiere;
        this.nomMatiere = nomMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
     
    
    
    public EtatdeStock(double valeur, int IdMatiere) {
        this.valeur = valeur;
        this.IdMatiere = IdMatiere;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public int getIdMatiere() {
        return IdMatiere;
    }

    public void setIdMatiere(int IdMatiere) {
        this.IdMatiere = IdMatiere;
    }
    
    
}
