package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PoketraMatiere {
//   idpoketramatiere | idpoketra | idmatiere | quantite  
    int idPoketraMatiere;
    int idPoketra;
    int idMatiere;
    double quantite;
    String nomMatiere;

    private PoketraMatiere(int idpoketra, String nomMatiere) {
        this.idPoketra = idpoketra;
        this.nomMatiere = nomMatiere;
    }

    
    public void insert(Connection connexion) throws SQLException{
        String requete = "insert into poketramatiere(idpoketra , idmatiere,quantite ) values(?,?,?)";
        try (PreparedStatement prepare = connexion.prepareStatement(requete)) {
            prepare.setInt(1, this.getIdPoketra());
            prepare.setInt(2, this.getIdMatiere());            
            prepare.setDouble(3, this.getQuantite());
            int trait = prepare.executeUpdate();
        }
        connexion.close();
    }
        public PoketraMatiere[] select(Connection connexion,int idpoketras) throws SQLException{
        String requete = "SELECT PM.*,m.nommatiere FROM poketramatiere pm join matiere m on m.idmatiere = pm.idmatiere where idpoketra =  "+idpoketras;
        System.out.println(requete);
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PoketraMatiere> array = new ArrayList<>();
        while(result.next()){
            int idpoketramatiere = result.getInt("idpoketramatiere");
            int idpoketra = result.getInt("idpoketra");
            int idmatiere = result.getInt("idmatiere");
            double quantite = result.getDouble("quantite");
            String nomMatiere = result.getString("nomMatiere");
            PoketraMatiere m = new PoketraMatiere(idpoketramatiere, idpoketra, idmatiere, quantite, nomMatiere);
            array.add(m);
        }
        connexion.close();
        return array.toArray(new PoketraMatiere[array.size()]);
    }
        public PoketraMatiere selectM(Connection connexion,int idMatiere) throws SQLException{
        String requete = "SELECT PM.*,m.nommatiere FROM poketramatiere pm \n" +
        "join matiere m on m.idmatiere = pm.idmatiere where idMatiere =  "+idMatiere;
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PoketraMatiere> array = new ArrayList<>();
        while(result.next()){
            int idpoketramatiere = result.getInt("idpoketramatiere");
            int idpoketra = result.getInt("idpoketra");
            int idmatiere = result.getInt("idmatiere");
            double quantite = result.getDouble("quantite");
            String nomMatiere = result.getString("nomMatiere");
            PoketraMatiere m = new PoketraMatiere(idpoketramatiere, idpoketra, idmatiere, quantite, nomMatiere);
            return m;
        }
        connexion.close();
        return null;
    }
    public PoketraMatiere[] selects(Connection connexion) throws SQLException{
        String requete = "select pm.idPoketra, t.nomType from poketraMatiere pm\n" +
        "join poketra po on po.idPoketra = pm.idPoketra\n" +
        "join TypePoketra t on t.idType = po.idType group by pm.idPoketra,t.nomType;";
        Statement stat = connexion.createStatement();
        ResultSet result = stat.executeQuery(requete);
        ArrayList<PoketraMatiere> array = new ArrayList<>();
        while(result.next()){
            
            int idpoketra = result.getInt("idpoketra");
            String nomMatiere = result.getString("nomType");
            PoketraMatiere m = new PoketraMatiere(idpoketra, nomMatiere);
            array.add(m);
        }
        connexion.close();
        return array.toArray(new PoketraMatiere[array.size()]);
    }
        
        public MatiereEnStock[] Verifier_stock_par_poketra(PoketraMatiere [] poketraMatiere,double quantite) throws Exception{
            ArrayList<MatiereEnStock> matireStock = new ArrayList<MatiereEnStock>();
            for(int i=0;i<poketraMatiere.length;i++){
                this.checkStock(poketraMatiere, poketraMatiere[i].getIdMatiere(), quantite);
                EtatdeStock etatdeStock = new EtatdeStock().etatParMatiere(Connexion.connect(),poketraMatiere[i].getIdMatiere());
                if(etatdeStock!=null){
                    
                    double etat = etatdeStock.getValeur()- poketraMatiere[i].getQuantite();
                    System.out.println( poketraMatiere[i].getQuantite());
                    MatiereEnStock matiere = new MatiereEnStock(poketraMatiere[i].getIdMatiere(),poketraMatiere[i].getQuantite());
                    matireStock.add(matiere);
                    if(etat<0){
                        throw new Exception("Quatite insufisant");
                    }
                }
            }
            Fabrication fab = new Fabrication(poketraMatiere[0].getIdPoketra(),quantite);
            fab.insert(Connexion.connect());
            return matireStock.toArray(new MatiereEnStock[matireStock.size()]);
        }
         public void checkStock(PoketraMatiere[] poketra,int idMatiere,double quantite) throws Exception{
            for(int i=0;i<poketra.length;i++){
                if(poketra[i].getIdMatiere()==idMatiere){
                   poketra[i].setQuantite(poketra[i].getQuantite()*quantite);
                }
                    
            }
         }

    public PoketraMatiere() {
    }
        
    public PoketraMatiere(int idPoketraMatiere, int idPoketra, int idMatiere, double quantite, String nomMatiere) {
        this.idPoketraMatiere = idPoketraMatiere;
        this.idPoketra = idPoketra;
        this.idMatiere = idMatiere;
        this.quantite = quantite;
        this.nomMatiere = nomMatiere;
    }
    
    public PoketraMatiere(int idPoketra, int idMatiere, double matiere) {
        this.idPoketra = idPoketra;
        this.idMatiere = idMatiere;
        this.quantite = matiere;
    }
    

    public PoketraMatiere(int idPoketraMatiere, int idPoketra, int idMatiere, double matiere) {
        this.idPoketraMatiere = idPoketraMatiere;
        this.idPoketra = idPoketra;
        this.idMatiere = idMatiere;
        this.quantite = matiere;
    }
    

    public int getIdPoketraMatiere() {
        return idPoketraMatiere;
    }

    public void setIdPoketraMatiere(int idPoketraMatiere) {
        this.idPoketraMatiere = idPoketraMatiere;
    }

    public int getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(int idPoketra) {
        this.idPoketra = idPoketra;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double matiere) {
        this.quantite = matiere;
    }
    
    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
}
