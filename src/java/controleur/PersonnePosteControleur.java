/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Connexion;
import model.Personne;
import model.Poste;
import model.PrixPoketra;
import java.sql.Date;
import model.PersonnePoste;
/**
 *
 * @author itu
 */
public class PersonnePosteControleur extends HttpServlet {

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
            out.println("<title>Servlet PersonnePosteControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonnePosteControleur at " + request.getContextPath() + "</h1>");
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
        try{
            Personne[] personne = new Personne().select(Connexion.connect());
            request.setAttribute("personne", personne);
            Poste[] poste = new Poste().select(Connexion.connect());
            request.setAttribute("poste", poste);
        } catch(Exception ex){
             ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("PersonnePoste.jsp");
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
        int idPersonne = Integer.valueOf(request.getParameter("personne"));
        int idPoste = Integer.valueOf(request.getParameter("poste"));
        Date debut = Date.valueOf(request.getParameter("debut"));
        PersonnePoste personnePoste = new PersonnePoste(idPersonne,idPoste,debut);
        try {
            personnePoste.insert(Connexion.connect());
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        RequestDispatcher dipache = request.getRequestDispatcher("index.jsp");
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
