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
import model.Poste;
import model.SalairePoste;

/**
 *
 * @author itu1
 */
public class SalairePosteControlleur extends HttpServlet {

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
            out.println("<title>Servlet SalairePosteControlleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SalairePosteControlleur at " + request.getContextPath() + "</h1>");
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
        try {
            Poste [] poste = new Poste().select(Connexion.connect());
            request.setAttribute("postes", poste);
            out.println(poste.length);
        } catch (Exception ex) {
            
            ex.printStackTrace(out);
        }
        RequestDispatcher dispache = request.getRequestDispatcher("salairePoste.jsp");
        dispache.forward(request, response); 
        
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
        try {
            String idPoste = request.getParameter("idPoste");
            String salaire = request.getParameter("salaire");
            String dates = request.getParameter("dates");
            
            SalairePoste salairePoste = new SalairePoste(Integer.valueOf(idPoste), Double.parseDouble(salaire), Date.valueOf(dates));
            
            salairePoste.insert(Connexion.connect());
            
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SalairePosteControlleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalairePosteControlleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispache = request.getRequestDispatcher("index.jsp");
        dispache.forward(request, response); 
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
