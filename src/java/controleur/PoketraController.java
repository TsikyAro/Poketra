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
import model.Poketra;
import model.PoketraMatiere;
import model.TypePoketra;

/**
 *
 * @author itu
 */
public class PoketraController extends HttpServlet {

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
            out.println("<title>Servlet PoketraController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PoketraController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            Look[] listlook = new Look().select(Connexion.connect());
            request.setAttribute("listlook", listlook);
            Poketra[] lPoketra= new Poketra().selectName(Connexion.connect());
            request.setAttribute("poketra", lPoketra);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("listePOketra.jsp");
        dipache.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int type =Integer.parseInt(request.getParameter("tp"));
        int taille =Integer.parseInt(request.getParameter("taille"));
        Poketra poketra = new Poketra(type,taille);
        try {
            poketra.insert(Connexion.connect());
            Poketra lastPoketra= new Poketra().select_last_enter(Connexion.connect());
            request.setAttribute("poketra", lastPoketra);
            Matiere [] metieres = new Matiere().select(Connexion.connect());
            request.setAttribute("matiere", metieres);
            
            PoketraMatiere [] poketraM = new PoketraMatiere().select(Connexion.connect(),lastPoketra.getIdPoketra());
            request.setAttribute("poketraM", poketraM);
            
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("poketraMatiere.jsp");
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
