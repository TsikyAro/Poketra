/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Benefice {
//     idpoketra | somme | prix_total |  nomtype  | nomtaille  
    int idpoketra;
    double benefice;

    public int getIdpoketra() {
        return idpoketra;
    }

    public void setIdpoketra(int idpoketra) {
        this.idpoketra = idpoketra;
    }

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }

    public Benefice(int idpoketra, double benefice) {
        this.idpoketra = idpoketra;
        this.benefice = benefice;
    }
    
    

    public Benefice() {
    }

    public Benefice[] select(Connection connexion,double min,double max) throws SQLException{
        String requete = "select * from benefice where benefice>="+min+" and benefice<="+max;
        System.out.println(requete);
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Benefice> array = new ArrayList<>();
        while(result.next()){
            Benefice m = new Benefice(result.getInt("idpoketra") , result.getDouble("benefice"));
            array.add(m);
        }
        connexion.close();
        return array.toArray(new Benefice[array.size()]);
    }
    
     public Benefice[] select(Connection connexion) throws SQLException{
        String requete = "select * from benefice";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Benefice> array = new ArrayList<>();
        while(result.next()){
            Benefice m = new Benefice(result.getInt("idpoketra") , result.getDouble("benefice"));
           array.add(m);
        }
        connexion.close();
        return array.toArray(new Benefice[array.size()]);
    }
    
}
