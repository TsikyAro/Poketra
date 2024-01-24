/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Taille;
import model.TypePoketra;

/**
 *
 * @author Ravo tina
 */
public class MatiereControleur extends HttpServlet {

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
            out.println("<title>Servlet MatiereControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MatiereControleur at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        out.println("huhu");
        try {
            TypePoketra[] type = new TypePoketra().select(Connexion.connect());
            Taille[] taille = new Taille().select(Connexion.connect());
            Matiere[] matiere = new Matiere().select(Connexion.connect());
            request.setAttribute("tp",type);
            request.setAttribute("taille", taille);
            request.setAttribute("matiere", matiere);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        RequestDispatcher dipache = request.getRequestDispatcher("creerPoketra.jsp");
        dipache.forward(request, response);
        
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
        PrintWriter out = response.getWriter();
        String name =request.getParameter("nomMatiere");
        Matiere matiere = new Matiere();
        matiere.setNomMatiere(name);
        try {
            matiere.insert(Connexion.connect());
            out.println("haha");
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("index.jsp");
        dipache.forward(request, response);
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
