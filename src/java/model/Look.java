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
public class Look {
    int idLook;
    String nomLook;

    public Look() {
     
    }

    public int getIdLook() {
        return idLook;
    }

    public void setIdLook(int idLook) {
        this.idLook = idLook;
    }

    public String getNomLook() {
        return nomLook;
    }

    public void setNomLook(String nomLook) {
        this.nomLook = nomLook;
    }

    public Look(int idLook, String nomLook) {
        this.idLook = idLook;
        this.nomLook = nomLook;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Look (nomlook) values(?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomLook());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
    
    public Look[] select(Connection connexion) throws SQLException{
        String requete = "select * from look";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Look> array = new ArrayList<Look>();
        while(result.next()){
            int id = result.getInt("idLook");
            String nom = result.getString("nomLook");
            Look l = new Look(id, nom);
            array.add(l);
        }
        connexion.close();
        return array.toArray(new Look[array.size()]);
    }
    
}
