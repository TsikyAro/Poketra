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
import model.Look;
import model.LookMatiere;
import model.Matiere;
import model.Poketra;

/**
 *
 * @author Ravo tina
 */
public class TrieControleur extends HttpServlet {

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
            out.println("<title>Servlet TrieControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TrieControleur at " + request.getContextPath() + "</h1>");
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
        String name =request.getParameter("look");
        try {
            Poketra[] lPoketra= new Poketra().selectNames(Connexion.connect(),name);
            request.setAttribute("poketra", lPoketra);
            Look[] listlook = new Look().select(Connexion.connect());
            request.setAttribute("listlook", listlook);
           Connexion.connect().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("listePOketra.jsp");
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
        
                PrintWriter out = response.getWriter();
        String name =request.getParameter("look");
        try {
            out.println("haha");
            LookMatiere[] lm = new LookMatiere().select_by_name(Connexion.connect(), name);
            Look[] listlook = new Look().select(Connexion.connect());
            request.setAttribute("listlook", listlook);
            request.setAttribute("lm", lm);
           Connexion.connect().close();
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("afficheliste.jsp");
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
