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
public class TypePoketra {
    int idType;
    String nomType;

    public TypePoketra(int idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }

    public TypePoketra() {
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into typePoketra ( nomType) values(?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomType());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
     
    public TypePoketra[] select(Connection connexion) throws SQLException{
        String requete = "select * from typePoketra";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<TypePoketra> array = new ArrayList<TypePoketra>();
        while(result.next()){
            int id = result.getInt("idType");
            String nom = result.getString("nomType");
            TypePoketra type = new TypePoketra(id, nom);
            array.add(type);
        }
        connexion.close();
        return array.toArray(new TypePoketra[array.size()]);
    }
    
}
