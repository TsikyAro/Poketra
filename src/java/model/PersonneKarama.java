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
 * @author itu
 */
public class PersonneKarama {
    int idPersonne_Karama;
    int idPersonne;
    double karama;

    public PersonneKarama(){}

    public PersonneKarama(int idPersonne, double karama) {
        this.idPersonne = idPersonne;
        this.karama = karama;
    }
    

    public PersonneKarama(int idPersonne_Karama, int idPersonne, double karama) {
        this.idPersonne_Karama = idPersonne_Karama;
        this.idPersonne = idPersonne;
        this.karama = karama;
    }

    public int getidPersonne_Karama() {
        return idPersonne_Karama;
    }

    public void setidPersonne_Karama(int idPersonne_Karama) {
        this.idPersonne_Karama = idPersonne_Karama;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public double getKarama() {
        return karama;
    }

    public void setKarama(double karama) {
        this.karama = karama;
    }

    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Personne_Karama( idPersonne , karama) values(?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdPersonne());
            prepare.setDouble(2, this.getKarama());
            int trait = prepare.executeUpdate();
        }
    }
    public  PersonneKarama[] select(Connection connexion) throws SQLException{
        String requete = "select * from Personne_Karama";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PersonneKarama> array = new ArrayList<PersonneKarama>();
        while(result.next()){
            int idPersonne_Karama = result.getInt("idPersonne_Karama");
            int idPersonne = result.getInt("idPersonne");
            double karama = result.getDouble("karama");
            PersonneKarama personneKarama = new PersonneKarama(idPersonne_Karama , idPersonne ,karama);
            array.add(personneKarama);
        }
        return array.toArray(new PersonneKarama[array.size()]);
    }
}
