package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Employe {
        //idemploye | idpersonne | date  
    int idEmploye;
    int idPersonne;
    Date date;
    String nomPersonne;
    Poste poste;

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Employe() {
    }
    
    public Employe[] EmployeTaux(Connection connexion) throws  Exception{
        Employe[] emp = this.select(connexion);
        LocalDate date = LocalDate.now();
        Date dates = Date.valueOf(date);
        for(int i =0; i<emp.length;i++){
            int annes = Personne.differenceEnAnnees(emp[i].getDate(),dates);
            System.out.println(annes);
            Poste poste =new Poste().postePersonne(annes, connexion);
            emp[i].setPoste(poste);
        }
        return emp;
    }
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Employe(idpersonne,date ) values(?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdPersonne());
            prepare.setDate(2, this.getDate());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
    public  Employe[] select(Connection connexion) throws SQLException{
        String requete = "select e.idpersonne,e.date,p.nompersonne from employe e join personne p on e.idpersonne=p.idpersonne; ";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Employe> array = new ArrayList<Employe>();
        while(result.next()){
            int idPersonne = result.getInt("idPersonne");
            Date date = result.getDate("date");
            String nom = result.getString("nomPersonne");
            Employe personne = new Employe( idPersonne ,date,nom);
            array.add(personne);
        }
//        connexion.close();
        return array.toArray(new Employe[array.size()]);
    }

    public Employe(int idPersonne, Date date, String nomPersonne) {
        this.idPersonne = idPersonne;
        this.date = date;
        this.nomPersonne = nomPersonne;
    }
    
    public Employe(int idPersonne, Date date) {
        this.idPersonne = idPersonne;
        this.date = date;
    }
    
    public Employe(int idEmploye, int idPersonne, Date date) {
        this.idEmploye = idEmploye;
        this.idPersonne = idPersonne;
        this.date = date;
    }
    

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
