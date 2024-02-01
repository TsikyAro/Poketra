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
public class Client {
    int idClient;
    String nomClient;
    int genre;

    public Client() {
    }
    

    public Client(String nomClient, int genre) {
        this.nomClient = nomClient;
        this.genre = genre;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Client(int idClient, String nomClient, int genre) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.genre = genre;
    }
    
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Client (nomClient, genre) values(?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomClient());
            prepare.setInt(2, this.getGenre());
            int trait = prepare.executeUpdate();
        }
    }
    
    public Client[] select(Connection connexion) throws SQLException{
        String requete = "select * from Client";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Client> array = new ArrayList<Client>();
        while(result.next()){
            Client type = new Client(result.getInt("idClient"), result.getString("nomClient"), result.getInt("genre"));
            array.add(type);
        }
        return array.toArray(new Client[array.size()]);
    } 
}
