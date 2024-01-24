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
public class SalairePoste {
    int idSalairePoste;
    int idPoste;
    Double salaire;
    Date dates;

    public SalairePoste(int idSalairePoste, int idPoste, Double salaire, Date dates) {
        this.idSalairePoste = idSalairePoste;
        this.idPoste = idPoste;
        this.salaire = salaire;
        this.dates = dates;
    }

    public SalairePoste(int idPoste, Double salaire, Date dates) {
        this.idPoste = idPoste;
        this.salaire = salaire;
        this.dates = dates;
    }

    public int getIdSalairePoste() {
        return idSalairePoste;
    }

    public void setIdSalairePoste(int idSalairePoste) {
        this.idSalairePoste = idSalairePoste;
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into SalairePoste (idPoste, Salaire, Dates) values(?, ?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdPoste());
            prepare.setDouble(2, this.getSalaire());
            prepare.setDate(3, this.getDates());
            int trait = prepare.executeUpdate();
        }
    }
    
    public SalairePoste[] select(Connection connexion) throws SQLException{
        String requete = "select * from SalairePoste";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<SalairePoste> array = new ArrayList<SalairePoste>();
        while(result.next()){
            SalairePoste  type = null;
            if(result.getInt("idPoste")==1){
                type = new SalairePoste(result.getInt("idSalairePoste"), result.getInt("idPoste"), result.getDouble("Salaire"), result.getDate("Dates"));
            }else if(result.getInt("idPoste")==2){
                type = new SalairePoste(result.getInt("idSalairePoste"), result.getInt("idPoste"), result.getDouble("Salaire")*2, result.getDate("Dates"));
            }else if(result.getInt("idPoste")==3){
                type = new SalairePoste(result.getInt("idSalairePoste"), result.getInt("idPoste"), (result.getDouble("Salaire")*3), result.getDate("Dates"));
            }
            
            array.add(type);
        }
        return array.toArray(new SalairePoste[array.size()]);
    } 
    
}
