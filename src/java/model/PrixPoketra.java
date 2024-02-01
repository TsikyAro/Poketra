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
public class PrixPoketra {
    int prixdate;
    int idmatiere;
    double prix;
    Date date;
    String nomtype;
    String nomtaille;
    String nommatiere;
    double quatiter;
    String nomlook;

    public PrixPoketra(int prixdate, int idmatiere, double prix, Date date, String nomtype, String nomtaille, String nommatiere, double quatiter, String nomlook) {
        this.prixdate = prixdate;
        this.idmatiere = idmatiere;
        this.prix = prix;
        this.date = date;
        this.nomtype = nomtype;
        this.nomtaille = nomtaille;
        this.nommatiere = nommatiere;
        this.quatiter = quatiter;
        this.nomlook = nomlook;
    }
    
    public PrixPoketra() {
    }

  

    public int getPrixdate() {
        return prixdate;
    }

    public void setPrixdate(int prixdate) {
        this.prixdate = prixdate;
    }

    public int getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(int idmatiere) {
        this.idmatiere = idmatiere;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNomtype() {
        return nomtype;
    }

    public void setNomtype(String nomtype) {
        this.nomtype = nomtype;
    }

    public String getNomtaille() {
        return nomtaille;
    }

    public void setNomtaille(String nomtaille) {
        this.nomtaille = nomtaille;
    }

    public String getNommatiere() {
        return nommatiere;
    }

    public void setNommatiere(String nommatiere) {
        this.nommatiere = nommatiere;
    }

    public double getQuatiter() {
        return quatiter;
    }

    public void setQuatiter(double quatiter) {
        this.quatiter = quatiter;
    }

    public String getNomlook() {
        return nomlook;
    }

    public void setNomlook(String nomlook) {
        this.nomlook = nomlook;
    }
      private PrixPoketra(Double prix, String nomtype, String nomtaille) {
          this.setPrix(prix);
          this.setNomtype(nomtype);
          this.setNomtaille(nomtaille);
    }
    public  PrixPoketra[] selectminmax(Connection connexion , String prixmin , String prixmax) throws SQLException{
        String requete = " select * from prix_total where prix_total>="+prixmin+" and prix_total<="+prixmax;
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PrixPoketra> array = new ArrayList<PrixPoketra>();
        while(result.next()){
            Double prix = result.getDouble("prix_total");
            String nomtype = result.getString("nomtype");
            String nomtaille = result.getString("nomtaille");
            PrixPoketra pketra = new PrixPoketra(prix,nomtype,nomtaille);
            array.add(pketra);
        }
        connexion.close();
        return array.toArray(new PrixPoketra[array.size()]);
    }
    
    
    
    public  PrixPoketra[] select(Connection connexion) throws SQLException{
        String requete = "select * from prix_total";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PrixPoketra> array = new ArrayList<PrixPoketra>();
        while(result.next()){
            Double prix = result.getDouble("prix_total");
            String nomtype = result.getString("nomtype");
            String nomtaille = result.getString("nomtaille");
            PrixPoketra pketra = new PrixPoketra(prix,nomtype,nomtaille);
            array.add(pketra);
        }
        connexion.close();
        return array.toArray(new PrixPoketra[array.size()]);
    }
    
}
