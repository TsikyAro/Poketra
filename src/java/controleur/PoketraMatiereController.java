/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Connexion;
import model.Matiere;
import model.Poketra;
import model.PoketraMatiere;

/**
 *
 * @author Tsiky Aro
 */
public class PoketraMatiereController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PoketraMatiereController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PoketraMatiereController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PoketraMatiere[] poketraMatiere;
        try {
            poketraMatiere = new PoketraMatiere().selects(Connexion.connect());
            request.setAttribute("poketraMatiere",poketraMatiere );
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
         RequestDispatcher dipache = request.getRequestDispatcher("fabricationPoketra.jsp");
        dipache.forward(request, response);
        
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int poketra = Integer.valueOf(request.getParameter("poketra"));
        int matiere = Integer.valueOf(request.getParameter("matiere"));
        double quantite = Double.valueOf(request.getParameter("quantite"));
        PoketraMatiere pkmatiere = new PoketraMatiere(poketra, matiere, quantite);
        PrintWriter out = response.getWriter();
        out.println("poketra "+request.getParameter("poketra")+" matiere "+request.getParameter("matiere")+" quantite "+request.getParameter("quantite"));
        try {
            pkmatiere.insert(Connexion.connect());
            Poketra lastPoketra = new Poketra().select_by_id(Connexion.connect(), poketra);
             request.setAttribute("poketra", lastPoketra);
            
             Matiere [] metieres = new Matiere().select(Connexion.connect());
            request.setAttribute("matiere", metieres);
            
            PoketraMatiere [] poketraM = pkmatiere.select(Connexion.connect(),poketra);
            request.setAttribute("poketraM", poketraM);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("poketraMatiere.jsp");
        dipache.forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
