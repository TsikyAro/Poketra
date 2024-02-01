package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class V_reste_poketra {
//      nomtype  | nomtaille | idpoketra | valeur  
    String nomType;
    String nomTaille;
    int idpoketra;
    double valeur;

    public V_reste_poketra() {
    }
    public V_reste_poketra[] select(Connection connexion) throws SQLException{
        String requete = "select * from V_reste_poketra";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<V_reste_poketra> array = new ArrayList<>();
        while(result.next()){
            V_reste_poketra m = new V_reste_poketra(result.getString("nomtype"),result.getString("nomtaille"),result.getInt("idpoketra"),result.getDouble("valeur"));
            array.add(m);
        }
        connexion.close();
        return array.toArray(new V_reste_poketra[array.size()]);
    }
    public V_reste_poketra(String nomType, String nomTaille, int idpoketra, double valeur) {
        this.nomType = nomType;
        this.nomTaille = nomTaille;
        this.idpoketra = idpoketra;
        this.valeur = valeur;
    }
    

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getNomTaille() {
        return nomTaille;
    }

    public void setNomTaille(String nomTaille) {
        this.nomTaille = nomTaille;
    }

    public int getIdpoketra() {
        return idpoketra;
    }

    public void setIdpoketra(int idpoketra) {
        this.idpoketra = idpoketra;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
}
