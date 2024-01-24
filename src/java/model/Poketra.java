package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Poketra {
    int idPoketra;
    int idType;
    int idTaille;
    String nomType;
    String nomTaille;
    String nomMatiere;
    String nomLook;

    public Poketra() {
    }
   
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Poketra(idType , idTaille ) values(?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdType());
            prepare.setInt(2, this.getIdTaille());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
     public Poketra[] selectNames(Connection connexion,String looks) throws SQLException{
        String requete = "select * from affichepoketra where nomlook ='"+looks+"'";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Poketra> array = new ArrayList<>();
        while(result.next()){
            String type = result.getString("nomType");
            String taille = result.getString("nomTaille");
            String matiere = result.getString("nomMatiere");
            String look = result.getString("nomLook");
            double quantiters = result.getDouble("quantiter");
            Poketra m = new Poketra(type, taille, matiere,look);
            array.add(m);
        }
        connexion.close();
        return array.toArray(new Poketra[array.size()]);
    }
    public Poketra[] selectName(Connection connexion) throws SQLException{
        String requete = "select * from affichepoketra";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Poketra> array = new ArrayList<>();
        while(result.next()){
            int idpoketra = result.getInt("idpoketra");
            String type = result.getString("nomType");
            String taille = result.getString("nomTaille");
            Poketra m = new Poketra(idpoketra,type, taille);
            array.add(m);
        }
         connexion.close();
        return array.toArray(new Poketra[array.size()]);
    }
    
    public Poketra[] select(Connection connexion) throws SQLException{
        String requete = "select * from Poketra";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Poketra> array = new ArrayList<>();
        while(result.next()){
            int idPoketras = result.getInt("idPoketra");
            int ididTypes = result.getInt("idType");
            int idTailles = result.getInt("idTaille");
            Poketra m = new Poketra(idPoketras, ididTypes, idTailles);
            array.add(m);
        }
        connexion.close();
        return array.toArray(new Poketra[array.size()]);
    }
    public Poketra select_last_enter(Connection connexion) throws SQLException{
        String requete = "select p.idpoketra, ty.nomType,t.nomtaille from poketra p join typepoketra ty on ty.idtype = p.idtype join taille t on p.idtaille = t.idtaille order by p.idpoketra desc;";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        if(result.next()){
            int idPoketras = result.getInt("idPoketra");
            String ididTypes = result.getString("nomType");
            String idTailles = result.getString("nomtaille");
            Poketra m = new Poketra(idPoketras, ididTypes, idTailles);
            return m;
        }
        connexion.close();
        return null;
    }
    public Poketra select_by_id(Connection connexion,int idpoketra) throws SQLException{
        String requete = "select p.idpoketra, ty.nomType,t.nomtaille from poketra p join typepoketra ty on ty.idtype = p.idtype join taille t on p.idtaille = t.idtaille where idpoketra = "+idpoketra+" order by p.idpoketra desc;";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        if(result.next()){
            int idPoketras = result.getInt("idPoketra");
            String ididTypes = result.getString("nomType");
            String idTailles = result.getString("nomtaille");
            Poketra m = new Poketra(idPoketras, ididTypes, idTailles);
            return m;
        }
        connexion.close();
        return null;
    }

    public Poketra(int idPoketra, String nomType, String nomTaille) {
        this.idPoketra = idPoketra;
        this.nomType = nomType;
        this.nomTaille = nomTaille;
    }
    
       public Poketra(int idPoketra, int idType, int idTaille) {
        this.idPoketra = idPoketra;
        this.idType = idType;
        this.idTaille = idTaille;
    }
    
    public Poketra(int idType, int idTaille) {
        this.idType = idType;
        this.idTaille = idTaille;
    }
    public Poketra(String nomType,String nomTaille,String nomMatiere,String nomLook){
        this.nomType = nomType;
        this.nomTaille = nomTaille;
        this.nomMatiere = nomMatiere;
        this.nomLook = nomLook;
    }
    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
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

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getNomLook() {
        return nomLook;
    }

    public void setNomLook(String nomLook) {
        this.nomLook = nomLook;
    }
   
    
}
