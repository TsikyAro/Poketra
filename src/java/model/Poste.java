package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Poste {
//    idposte | nom | annee | karama | date 
    int idPoste;
    String nomPoste;
    int annee;
    int karama;
    Date dates;
    double salaire;

    public Poste() {
    }
    
        public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Poste (nom, annee,karama,date) values( ?,?,?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setString(1, this.getNomPoste());
            prepare.setInt(2, this.getAnnee());
            prepare.setInt(3, this.getKarama());
            prepare.setDate(4, this.getDates());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
 
    public Poste[] select(Connection connexion) throws SQLException{
        String requete = "select * from salaireposte"; // eto soloina anle view
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Poste> array = new ArrayList<Poste>();
        while(result.next()){
            Poste type = new Poste(result.getInt("idPoste"), result.getString("nomPoste"), result.getInt("annee"),result.getInt("karama"),result.getDate("date"),result.getDouble("salaire"));
            array.add(type);
        }
        connexion.close();
        return array.toArray(new Poste[array.size()]);
    } 
   
    public Poste postePersonne(int annes,Connection connexion) throws Exception{
        String requete = "select * from salaireposte where annee<="+annes+" order by annee desc limit 1"; // eto soloina anle view
        System.out.println(requete);
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Poste> array = new ArrayList<Poste>();
        while(result.next()){
            Poste type = new Poste(result.getInt("idPoste"), result.getString("nom"), result.getInt("annee"),result.getInt("karama"),result.getDate("date"),result.getDouble("salaire"));
            array.add(type);
        }
//        connexion.close();
        return array.get(0);
    }

    public Poste(String nomPoste, int annee, int karama, Date date) {
        this.nomPoste = nomPoste;
        this.annee = annee;
        this.karama = karama;
        this.dates = date;
    }
    
    public Poste(int idPoste, String nomPoste, int annee, int karama, Date date, double salaire) {
        this.idPoste = idPoste;
        this.nomPoste = nomPoste;
        this.annee = annee;
        this.karama = karama;
        this.dates = date;
        this.salaire = salaire;
    }
    

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getKarama() {
        return karama;
    }

    public void setKarama(int karama) {
        this.karama = karama;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date date) {
        this.dates = date;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
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
}
