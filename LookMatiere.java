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

/**
 *
 * @author itu1
 */
public class LookMatiere {
    int idLookM;
    int idMatiere;
    int idLook;
    String nomLook;
    String nomMatiere;

    public int getIdLookM() {
        return idLookM;
    }

    public void setIdLookM(int idLookM) {
        this.idLookM = idLookM;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
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

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public LookMatiere(int idLookM, String nomLook, String nomMatiere) {
        this.idLookM = idLookM;
        this.nomLook = nomLook;
        this.nomMatiere = nomMatiere;
    }
    
    public LookMatiere[] select(Connection connexion) throws SQLException{
        String requete = "select idLookM, nomLook, nomMatiere from lookMatiere";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<LookMatiere> array = new ArrayList<LookMatiere>();
        while(result.next()){
            int id = result.getInt("idLookM");
            String look = result.getString("nomLook");
            String matiere = result.getString("nomMatiere");
            LookMatiere lM = new LookMatiere(id, look, matiere);
            array.add(lM);
        }
        return array.toArray(new LookMatiere[array.size()]);
    }
    
}
