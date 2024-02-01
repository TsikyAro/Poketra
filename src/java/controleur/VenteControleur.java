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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Client;
import model.Connexion;
import model.Panier;
import model.Poketra;
import model.Vente;

/**
 *
 * @author itu
 */
public class VenteControleur extends HttpServlet {

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
            out.println("<title>Servlet VenteControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VenteControleur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
        Poketra[] poketra = new Poketra().selectName(Connexion.connect());
        request.setAttribute("poketra", poketra);
        Client[] client = new Client().select(Connexion.connect());
        request.setAttribute("client", client);
        HttpSession session = request.getSession();
        if(session.getAttribute("panier") != null){
            ArrayList<Panier> paniers = (ArrayList<Panier>)session.getAttribute("panier");
            request.setAttribute("paniers", paniers);
        }
        
        } catch(Exception ex){
            ex.printStackTrace();
        }
        RequestDispatcher dipache = request.getRequestDispatcher("vente.jsp");
        dipache.forward(request, response);
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
        
        String prod = request.getParameter("produit");
//        out.println(prod);
        String [] table = prod.split(";");
//        out.println(table[0]);
        int idProduit = Integer.valueOf(table[0]);
        int nbrProduit =  Integer.valueOf(request.getParameter("quantite"));
        Panier panier = new Panier(idProduit,nbrProduit);
        panier.setNomPoketra(table[1]);
        HttpSession session = request.getSession();
        ArrayList<Panier> paniers = new ArrayList<Panier>();
        if(session.getAttribute("panier")!= null){
            paniers = (ArrayList<Panier>)session.getAttribute("panier");
        }
        paniers.add(panier);
        paniers = Panier.ajoutPanier(paniers);
         session.setAttribute("panier", paniers);
        doGet(request,response);
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
