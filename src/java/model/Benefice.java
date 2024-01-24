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
    double somme;
    double Prix_total;
    String Nomtype;
    String Nomtaille;
    double benefice;

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }
    

    public Benefice() {
    }

    public Benefice(int idpoketra, double dure, double Prix_total, String Nomtype, String Nomtaille) {
        this.idpoketra = idpoketra;
        this.somme = dure;
        this.Prix_total = Prix_total;
        this.Nomtype = Nomtype;
        this.Nomtaille = Nomtaille;
    }

    public int getIdpoketra() {
        return idpoketra;
    }

    public void setIdpoketra(int idpoketra) {
        this.idpoketra = idpoketra;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double dure) {
        this.somme = dure;
    }


    public double getPrix_total() {
        return Prix_total;
    }

    public void setPrix_total(double Prix_total) {
        this.Prix_total = Prix_total;
    }

    public String getNomtype() {
        return Nomtype;
    }

    public void setNomtype(String Nomtype) {
        this.Nomtype = Nomtype;
    }

    public String getNomtaille() {
        return Nomtaille;
    }

    public void setNomtaille(String Nomtaille) {
        this.Nomtaille = Nomtaille;
    }
    
    
     public Benefice select(Connection connexion,int idpoketra) throws SQLException{
        String requete = "select idpoketra , somme ,Prix_total,Nomtype, Nomtaille from Benefice where idpoketra="+idpoketra;
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Benefice> array = new ArrayList<>();
        while(result.next()){
            Benefice m = new Benefice(result.getInt("idpoketra") , result.getDouble("somme"),result.getDouble("Prix_total"), result.getString("Nomtype") , result.getString("Nomtaille"));
            return m;
        }
        connexion.close();
        return null;
    }
     public Benefice  getBenefice(Benefice benefice){
        if(benefice.getNomtaille()=="Large"){
            benefice.setSomme(benefice.getSomme()*2);
        }
        return benefice;
     }
     public void calculeBenefice(Benefice benefice,PoketraPrix poketra){
         benefice = benefice.getBenefice(benefice);
         double prix = benefice.getPrix_total()+benefice.getSomme();
         this.setBenefice(poketra.getPrixPoketra()- prix);
     } 
    
}
