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
public class Poste {
    int idPoste;
    String nomPoste;
    int hierarchie;

    public Poste(String nomPoste, int hierarchie) {
        this.nomPoste = nomPoste;
        this.hierarchie = hierarchie;
    }
    
    public Poste(int idPoste, String nomPoste, int hierarchie) {
        this.idPoste = idPoste;
        this.nomPoste = nomPoste;
        this.hierarchie = hierarchie;
    }

    public Poste() {
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public int getHierarchie() {
        return hierarchie;
    }

    public void setHierarchie(int hierarchie) {
        this.hierarchie = hierarchie;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Poste (nomPoste, hierachie) values( ?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomPoste());
            prepare.setInt(2, this.getHierarchie());
            int trait = prepare.executeUpdate();
        }
    }
 
    public Poste[] select(Connection connexion) throws SQLException{
        String requete = "select * from Poste";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Poste> array = new ArrayList<Poste>();
        while(result.next()){
            Poste type = new Poste(result.getInt("idPoste"), result.getString("nomPoste"), result.getInt("hierachie"));
            array.add(type);
        }
        return array.toArray(new Poste[array.size()]);
    } 
}
