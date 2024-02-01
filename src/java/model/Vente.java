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
 * @author itu
 */
public class Vente {
    int idVente;
    int idClient;
    Date dates;

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
    public  Vente selectLastEnter(Connection connexion) throws SQLException{
        String requete = "select * from Vente order by idvente desc";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Vente> array = new ArrayList<Vente>();
        while(result.next()){
            Vente vente = new Vente(result.getInt(1),result.getInt(2),result.getDate(3));
            return vente;
        }
        return new Vente();
    }

    public Vente() {
    }
    public  Vente[] select(Connection connexion) throws SQLException{
        String requete = "select * from Vente";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Vente> array = new ArrayList<Vente>();
        while(result.next()){
            Vente vente = new Vente(result.getInt(1),result.getInt(2),result.getDate(3));
            array.add(vente);
        }
        return array.toArray(new Vente[array.size()]);
    }
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Vente( idClient,dates) values(?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {;
            prepare.setInt(1, this.getIdClient());
            prepare.setDate(2, this.getDates());
            int trait = prepare.executeUpdate();
        }

    }
    public int getIdVente(){
        return this.idVente;
    }
    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Vente( int idClient,Date dates ) {
        this.idClient = idClient;
        this.dates =dates;
    }

    public Vente(int idVente, int idClient, Date dates) {
        this.idVente = idVente;
        this.idClient = idClient;
        this.dates = dates;
    }
    

}
