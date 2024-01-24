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
public class PoketraPrix {
    int idPoketraPrix;
    int idPoketra;
    double prixPoketra;

    public PoketraPrix(int idPoketra, double prixPoketra) {
        this.idPoketra = idPoketra;
        this.prixPoketra = prixPoketra;
    }
    

    public PoketraPrix(int idPoketraPrix, int idPoketra, double prixPoketra) {
        this.idPoketraPrix = idPoketraPrix;
        this.idPoketra = idPoketra;
        this.prixPoketra = prixPoketra;
    }

    public PoketraPrix() {
    }

    public int getIdPoketraPrix() {
        return idPoketraPrix;
    }

    public void setIdPoketraPrix(int idPoketraPrix) {
        this.idPoketraPrix = idPoketraPrix;
    }

    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public double getPrixPoketra() {
        return prixPoketra;
    }

    public void setPrixPoketra(double prixPoketra) {
        this.prixPoketra = prixPoketra;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Poketra_Prix ( idPoketra,  prix_poketra) values( ?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdPoketra());
            prepare.setDouble(2, this.getPrixPoketra());
            int trait = prepare.executeUpdate();
        }
    }

    public PoketraPrix select_byId(Connection connexion,int idpoketra) throws SQLException{
        String requete = "select * from Poketra_Prix where idpoketra = "+idpoketra;
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PoketraPrix> array = new ArrayList<PoketraPrix>();
        while(result.next()){
            PoketraPrix type = new PoketraPrix(result.getInt("idPoketra_Prix"), result.getInt("idPoketra"), result.getDouble("prix_Poketra"));
            return type;
        }
        return null;
    }
    public PoketraPrix[] select(Connection connexion) throws SQLException{
        String requete = "select * from Poketra_Prix";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PoketraPrix> array = new ArrayList<PoketraPrix>();
        while(result.next()){
            PoketraPrix type = new PoketraPrix(result.getInt("idPoketra_Prix"), result.getInt("idPoketra"), result.getDouble("prixPoketra"));
            array.add(type);
        }
        return array.toArray(new PoketraPrix[array.size()]);
    }
    
}
