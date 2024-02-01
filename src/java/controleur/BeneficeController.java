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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Benefice;
import model.Connexion;
import model.Poketra;
import model.PoketraPrix;

/**
 *
 * @author Tsiky Aro
 */
public class BeneficeController extends HttpServlet {

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
            out.println("<title>Servlet BeneficeController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BeneficeController at " + request.getContextPath() + "</h1>");
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
            Benefice [] benefice = new Benefice().select(Connexion.connect());
            request.setAttribute("poketra",benefice);
            PrintWriter out = response.getWriter();
            out.println(benefice.length);
            RequestDispatcher dispatch = request.getRequestDispatcher("Benefice.jsp");
            dispatch.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double min = Double.valueOf(request.getParameter("min"));
        double max = Double.valueOf(request.getParameter("max"));
        ArrayList<Benefice>  benefices = new ArrayList<Benefice>();
        try {
            Benefice [] benefice = new Benefice().select(Connexion.connect(),min,max);
            request.setAttribute("poketra",benefice);
            RequestDispatcher dispatch = request.getRequestDispatcher("Benefice.jsp");
            dispatch.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
