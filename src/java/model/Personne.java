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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author itu
 */
public class Personne {
    int idPersonne;
    String NomPersonne;
    String tache;
    

    public int CalculDate(Date debut,Date omena){
         int differenceAnnees = differenceEnAnnees(debut, omena);
         return differenceAnnees;
    }
    private static java.util.Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Fonction pour calculer la différence en années entre deux dates
    public static int differenceEnAnnees(java.util.Date date1, java.util.Date date2) {
        long differenceEnMillis = date2.getTime() - date1.getTime();
        long differenceEnJours = differenceEnMillis / (24 * 60 * 60 * 1000);
        return (int) (differenceEnJours / 365);
    }

    public Personne(String NomPersonne, String tache) {
        this.NomPersonne = NomPersonne;
        this.tache = tache;
    }
    

    public Personne(){}

    public Personne(int idPersonne, String NomPersonne, String tache) {
        this.idPersonne = idPersonne;
        this.NomPersonne = NomPersonne;
        this.tache = tache;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNomPersonne() {
        return NomPersonne;
    }

    public void setNomPersonne(String NomPersonne) {
        this.NomPersonne = NomPersonne;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Personne( NomPersonne , tache) values(?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomPersonne());
            prepare.setString(2, this.getTache());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
    public  Personne[] select(Connection connexion) throws SQLException{
        String requete = "select * from personne";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Personne> array = new ArrayList<Personne>();
        while(result.next()){
            int idPersonne = result.getInt("idPersonne");
            String NomPersonne = result.getString("NomPersonne");
            String Tache = result.getString("tache");
            Personne personne = new Personne(idPersonne , NomPersonne ,Tache);
            array.add(personne);
        }
        connexion.close();
        return array.toArray(new Personne[array.size()]);
    }
}
