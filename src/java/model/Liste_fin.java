/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ravo tina
 */
public class Liste_fin {
     String nompersonne; 
     Date datedebu;
     String nomposte;
     double salaire;
     Date dates;

    public Liste_fin() {
    }
     
    public Liste_fin(String nompersonne, Date datedebu, String nomposte, double salaire, Date dates) {
        this.nompersonne = nompersonne;
        this.datedebu = datedebu;
        this.nomposte = nomposte;
        this.salaire = salaire;
        this.dates = dates;
    }
     
     

    public String getNompersonne() {
        return nompersonne;
    }

    public void setNompersonne(String nompersonne) {
        this.nompersonne = nompersonne;
    }

    public Date getDatedebu() {
        return datedebu;
    }

    public void setDatedebu(Date datedebu) {
        this.datedebu = datedebu;
    }

    public String getNomposte() {
        return nomposte;
    }

    public void setNomposte(String nomposte) {
        this.nomposte = nomposte;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
    
    
    public Liste_fin[] select(Connection connexion) throws SQLException{
        String requete = "select * from liste_finale";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Liste_fin> array = new ArrayList<Liste_fin>();
        while(result.next()){
            Liste_fin type =null;
            if(result.getString("nomposte").equalsIgnoreCase("Ouvrier")){
                type = new Liste_fin(result.getString("nompersonne"), result.getDate("datedebu"), result.getString("nomposte"), result.getDouble("salaire") , result.getDate("dates"));
            }
            else if(result.getString("nomposte").equalsIgnoreCase("senior")){
                type = new Liste_fin(result.getString("nompersonne"), result.getDate("datedebu"), result.getString("nomposte"), result.getDouble("salaire")*2 , result.getDate("dates"));

            }else if(result.getString("nomposte").equalsIgnoreCase("expert")){
                 type = new Liste_fin(result.getString("nompersonne"), result.getDate("datedebu"), result.getString("nomposte"), result.getDouble("salaire")*3 , result.getDate("dates"));

            }
            array.add(type);
        }
        return array.toArray(new Liste_fin[0]);
    }
    
}
