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
import model.Matiere;

/**
 *
 * @author Ravo tina
 */
public class LookControleur extends HttpServlet {

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
            out.println("<title>Servlet LookControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LookControleur at " + request.getContextPath() + "</h1>");
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
            out.println("haha");
            Look[] looks = new Look().select(Connexion.connect());
            Matiere[] matieres = new Matiere().select(Connexion.connect());
            out.println(looks.length+"   "+matieres.length);
            request.setAttribute("looks", looks);
            request.setAttribute("matieres", matieres);
            Connexion.connect().close();
            
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("listMatiere.jsp");
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
        String name =request.getParameter("nomMatiere");
        Look matiere = new Look();
        matiere.setNomLook(name);
        try {
            matiere.insert(Connexion.connect());
            out.println("haha");
            Look[] looks = new Look().select(Connexion.connect());
            Matiere[] matieres = new Matiere().select(Connexion.connect());
            out.println(looks.length+"   "+matieres.length);
            request.setAttribute("looks", looks);
            request.setAttribute("matieres", matieres);
            Connexion.connect().close();
            
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("listMatiere.jsp");
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
