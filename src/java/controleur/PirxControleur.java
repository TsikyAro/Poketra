/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Connexion;
import model.Matiere;
import model.PrixDate;
import model.PrixPoketra;

/**
 *
 * @author itu
 */
public class PirxControleur extends HttpServlet {

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
            out.println("<title>Servlet PirxControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PirxControleur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Matiere[] matieres = new Matiere().select(Connexion.connect());
            
        request.setAttribute("matieres",matieres);
        } catch (Exception ex) {
            ex.printStackTrace();     
        } 
        RequestDispatcher dipache = request.getRequestDispatcher("insertionPrix.jsp");
        dipache.forward(request, response);
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idMatiere = Integer.valueOf(request.getParameter("idMatiere"));
        double prix = Double.valueOf(request.getParameter("prix"));
        Date date = Date.valueOf(request.getParameter("date"));
        PrixDate prixDate = new PrixDate(idMatiere, prix, date);
        try {
            prixDate.insert(Connexion.connect());
            PrixPoketra[] prixPoketra = new PrixPoketra().select(Connexion.connect());
        request.setAttribute("prixPoketra", prixPoketra);
        } catch (Exception ex) {
            Logger.getLogger(PirxControleur.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        RequestDispatcher dipache = request.getRequestDispatcher("PrixPoketra.jsp");
        dipache.forward(request, response);
        processRequest(request, response);
        
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
