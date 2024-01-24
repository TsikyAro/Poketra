package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class PersonnePoste {
    int idPersonnePoste;
    int idPersonne;
    int idPoste;
    Date debut;
    Date fin;

    public PersonnePoste(int idPersonnePoste, int idPersonne, int idPoste, Date debut, Date fin) {
        this.idPersonnePoste = idPersonnePoste;
        this.idPersonne = idPersonne;
        this.idPoste = idPoste;
        this.debut = debut;
        this.fin = fin;
    }

    public PersonnePoste(int idPersonne, int idPoste, Date debut) {
        this.idPersonne = idPersonne;
        this.idPoste = idPoste;
        this.debut = debut;
    }

    

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    
    public  PersonnePoste[] select(Connection connexion) throws SQLException{
        String requete = "select * from PersonnePoste";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PersonnePoste> array = new ArrayList<PersonnePoste>();
        while(result.next()){
            PersonnePoste personnePoste = new PersonnePoste(result.getInt(0),result.getInt(1),result.getInt(2),result.getDate(3),result.getDate(4));
            array.add(personnePoste);
        }
        return array.toArray(new PersonnePoste[array.size()]);
    }
    public  PersonnePoste[] selectFinNull(Connection connexion) throws SQLException{
        String requete = "select * from PersonnePoste where Fin is null";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PersonnePoste> array = new ArrayList<PersonnePoste>();
        while(result.next()){
            PersonnePoste personnePoste = new PersonnePoste(result.getInt(0),result.getInt(1),result.getInt(2),result.getDate(3),result.getDate(4));
            array.add(personnePoste);
        }
        return array.toArray(new PersonnePoste[array.size()]);
    }
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into PersonnePoste(idPersonne , idPoste , Debut ) values(?,?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdPersonne());
            prepare.setInt(2, this.getIdPoste());
            prepare.setDate(3, this.getDebut());
            int trait = prepare.executeUpdate();
        }
    }
    public void Update(Connection connexion) throws SQLException{
        String requete = "update  PersonnePoste set Fin = ?";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setDate(1, this.getFin());
            int trait = prepare.executeUpdate();
        }
    }
    

    public int getIdPersonnePoste() {
        return idPersonnePoste;
    }

    public void setIdPersonnePoste(int idPersonnePoste) {
        this.idPersonnePoste = idPersonnePoste;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }
    

    
}

