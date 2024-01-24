/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author itu1
 */
public class MatiereEnStock {
    int idMatEnStock;
    int idMatiere;
    double quantieEnStock;
    Date date;

    public MatiereEnStock(int idMatiere, double quantite, Date date) {
       this.idMatiere = idMatiere;
       this.quantieEnStock = quantite;
        this.date = date;//To change body of generated methods, choose Tools | Templates.
    }

    public MatiereEnStock(int idMatiere, double quantieEnStock) {
        this.idMatiere = idMatiere;
        this.quantieEnStock = quantieEnStock;
    }

   

    public int getIdMatEnStock() {
        return idMatEnStock;
    }

    public void setIdMatEnStock(int idMatEnStock) {
        this.idMatEnStock = idMatEnStock;
    }

    public MatiereEnStock(int idMatEnStock, int idMatiere, double quantieEnStock, Date date) {
        this.idMatEnStock = idMatEnStock;
        this.idMatiere = idMatiere;
        this.quantieEnStock = quantieEnStock;
        this.date = date;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public double getQuantieEnStock() {
        return quantieEnStock;
    }

    public void setQuantieEnStock(double quantieEnStock) {
        this.quantieEnStock = quantieEnStock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Matiere_en_stock ( idmatiere,quantite_en_stok ,dates ) values(?, ?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdMatiere());
            prepare.setDouble(2, this.getQuantieEnStock());
            prepare.setDate(3, this.getDate());
            int trait = prepare.executeUpdate();
        }
    }
    public void insertSortie(Connection connexion) throws SQLException{
        String requete = "insert into sortie (idmatiere ,quantite, dates ) values(?, ?, ?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdMatiere());
            prepare.setDouble(2, this.getQuantieEnStock());
            prepare.setDate(3, this.getDate());
            int trait = prepare.executeUpdate();
        }
    }
    
    
    public MatiereEnStock[] select(Connection connexion) throws SQLException{
        String requete = "select * from Matiere_en_stock";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<MatiereEnStock> array = new ArrayList<MatiereEnStock>();
        while(result.next()){
            int id = result.getInt("idMatiere_en_stock");
            int idM = result.getInt("idMatiere");
            double qtStock = result.getDouble("Quantite_en_stock");
            Date d = result.getDate("dates");
            MatiereEnStock rep = new MatiereEnStock(id, idM, qtStock, d);
            array.add(rep);
        }
        return array.toArray(new MatiereEnStock[array.size()]);
    }
    
    public MatiereEnStock selectById(Connection connexion, int id) throws SQLException{
        MatiereEnStock rep = null;
        String requete = "select * from Matiere_en_stock where idMatiere_en_stock=?";
        PreparedStatement prepare = connexion.prepareStatement(requete);
        prepare.setInt(1, id);
        ResultSet result = prepare.executeQuery();
        while(result.next()){
            int idM = result.getInt("idMatiere");
            double qtStock = result.getDouble("Quantite_en_stock");
            Date d = result.getDate("dates");
            rep = new MatiereEnStock(id, idM, qtStock, d);
        }
        return rep;
    }
    
}
