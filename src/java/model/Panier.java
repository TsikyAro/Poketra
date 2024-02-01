
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Panier {
    int idPanier;
    int idVente;
    int idPoketra;
    int quantite;
    String nomPoketra;

    public Panier(int idPoketra, int quantite, String nomPoketra) {
        this.idPoketra = idPoketra;
        this.quantite = quantite;
        this.nomPoketra = nomPoketra;
    }
    
     public static ArrayList<Panier> ajoutPanier(List<Panier> originalList) {
        Map<Integer, Panier> priceSumMap = new HashMap<>();
        for (Panier product : originalList) {
            priceSumMap.merge(
                product.getIdPoketra(),
                product,
                (existingProduct, newProduct)->
                    new Panier(
                        existingProduct.getIdPoketra(),
                        existingProduct.getQuantite() + newProduct.getQuantite(),
                        existingProduct.getNomPoketra()
                    )
            );
        }
        ArrayList<Panier>  uniqueList= new ArrayList<>(priceSumMap.values());

        return uniqueList;
    }

    public Panier() {
    }
    
    public Panier[] setIdVentePanier(ArrayList<Panier> paniers,int idVente){
        for(int i =0;i<paniers.size();i++){
            paniers.get(i).setIdVente(idVente);
        }
        return paniers.toArray(new Panier[paniers.size()]);
    }
    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into Panier( idVente,idPoketra,quantite) values(?,?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {;
            prepare.setInt(1, this.getIdVente());
            prepare.setInt(2,this.getIdPoketra());
            prepare.setInt(3, this.getQuantite());
            int trait = prepare.executeUpdate();
        }

    }

    public String getNomPoketra() {
        return nomPoketra;
    }

    public void setNomPoketra(String nomPoketra) {
        this.nomPoketra = nomPoketra;
    }

    public Panier( int idPoketra, int quantite) {
        this.idPoketra = idPoketra;
        this.quantite = quantite;
    }
    
    public Panier(int idPanier, int idVente, int idPoketra, int quantite) {
        this.idPanier = idPanier;
        this.idVente = idVente;
        this.idPoketra = idPoketra;
        this.quantite = quantite;
    }
    
    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
}
