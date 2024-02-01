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
 * @author itu1
 */
public class PoketraMaker {
    int idPoketraMaker;
    int idPoketra;
    int idPersonne;
    double duree;
    int idFabrication;

    public int getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(int idFabrication) {
        this.idFabrication = idFabrication;
    }
    

    public PoketraMaker(int idPoketra, int idPersonne, double duree,int idFabrication) {
        this.idPoketra = idPoketra;
        this.idPersonne = idPersonne;
        this.duree = duree;
        this.idFabrication = idFabrication;
    }
    
    

    public PoketraMaker(int idPoketraMaker, int idPoketra, int idPersonne, double duree) {
        this.idPoketraMaker = idPoketraMaker;
        this.idPoketra = idPoketra;
        this.idPersonne = idPersonne;
        this.duree = duree;
    }

    public int getIdPoketraMaker() {
        return idPoketraMaker;
    }

    public void setIdPoketraMaker(int idPoketraMaker) {
        this.idPoketraMaker = idPoketraMaker;
    }

    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into poketra_Maker ( idPoketra, idPersonne,idfabrication,dure) values( ?, ?, ?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdPoketra());
            prepare.setInt(2, this.getIdPersonne());            
            prepare.setInt(3, this.getIdFabrication());
            prepare.setDouble(4, this.getDuree());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }

    public PoketraMaker[] select(Connection connexion) throws SQLException{
        String requete = "select * from Poketra_Maker";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PoketraMaker> array = new ArrayList<PoketraMaker>();
        while(result.next()){
            PoketraMaker type = new PoketraMaker(result.getInt("idPoketra_Maker"), result.getInt("idPoketra"), result.getInt("idPersonne"), result.getDouble("duree"));
            array.add(type);
        }
        connexion.close();
        return array.toArray(new PoketraMaker[array.size()]);
    }    

}
