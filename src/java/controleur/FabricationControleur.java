package controleur;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Connexion;
import model.EtatdeStock;
import model.Fabrication;
import model.Matiere;
import model.MatiereEnStock;
import model.PoketraMatiere;

public class FabricationControleur extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FabricationControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FabricationControleur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            EtatdeStock [] etat;
        try {
            int idmatiere = Integer.valueOf(request.getParameter("matiere"));
            etat = new EtatdeStock().etatParStockparMatiere(Connexion.connect(),idmatiere);
            request.setAttribute("etat_de_stock", etat);
            Matiere [] matieres = new Matiere().select(Connexion.connect());
            request.setAttribute("matiere", matieres);
            RequestDispatcher dispatch = request.getRequestDispatcher("MatiereEnStock.jsp");
            dispatch.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
           
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPoketra = Integer.valueOf(request.getParameter("poketraMatiere"));
        double quantite = Double.valueOf(request.getParameter("quantite"));
        Date dates = Date.valueOf(request.getParameter("date"));
        PrintWriter out = response.getWriter();
//        out.println("Quatite insufisant "+request.getParameter("poketraMatiere"));  
        PoketraMatiere pm = new PoketraMatiere();
        Date date = new Date(2023, 12,12);
        try {
            PoketraMatiere[] poketraMatiere = pm.select(Connexion.connect(),idPoketra);
            MatiereEnStock[] verification = pm.Verifier_stock_par_poketra(poketraMatiere, quantite,dates);
            if(verification.length > 0){
                for(int i = 0;i< verification.length;i++){
                    verification[i].setDate(date);
                    verification[i].insertSortie(Connexion.connect());
                }
                
            }
           EtatdeStock [] etat = new EtatdeStock().etatParStock(Connexion.connect());
           request.setAttribute("etat_de_stock", etat);
           Matiere [] matieres = new Matiere().select(Connexion.connect());
           request.setAttribute("matiere", matieres);
            RequestDispatcher dispatch = request.getRequestDispatcher("MatiereEnStock.jsp");
            dispatch.forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur de conversion : Veuillez entrer une valeur valide pour poketraMatiere.");
            e.printStackTrace();
            
        }
            
 }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
