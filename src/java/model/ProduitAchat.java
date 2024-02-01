package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProduitAchat {
    double nombre_achat;

    public static Double[] statistiqueTotal (Connection connexion) throws SQLException{
        String requete = "select * from V_achatTotal order by genre asc";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Double> array = new ArrayList<Double>();
        while(result.next()){
            double type = result.getDouble(2);
            array.add(type);
        }
        return array.toArray(new Double[array.size()]);
    }
    public static Double[] statistiqueParProduit (Connection connexion,int idProduit) throws SQLException{
        String requete = "select * from V_ProduitTotal where idpoketra="+ idProduit+"order by genre asc";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<Double> array = new ArrayList<Double>();
        while(result.next()){
            double type = result.getDouble(3);
            array.add(type);
        }
        return array.toArray(new Double[array.size()]);
    }
}
