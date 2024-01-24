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

/**
 *
 * @author Ravo tina
 */
public class LookMatiereControleur extends HttpServlet {

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
            out.println("<title>Servlet LookMatiereControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LookMatiereControleur at " + request.getContextPath() + "</h1>");
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
        String erreur = request.getParameter("erreur");
        if(erreur!=null){
            request.setAttribute("erreur", erreur);
        }
        try {
            LookMatiere[] lm = new LookMatiere().select(Connexion.connect());
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
        int id_matiere =Integer.parseInt(request.getParameter("matiere"));
        int id_look =Integer.parseInt(request.getParameter("type"));
        LookMatiere look = new LookMatiere();
        look.setIdLook(id_look);
        look.setIdMatiere(id_matiere);
        try {
            look.insert(Connexion.connect());
           Connexion.connect().close();
           response.sendRedirect("LookMatiereControleur");
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
            response.sendRedirect("LookMatiereControleur?erreur="+ex.getMessage());

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
