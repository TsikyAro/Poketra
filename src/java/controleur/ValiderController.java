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
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Connexion;
import model.EtatdeStock;
import model.Panier;
import model.Vente;

public class ValiderController extends HttpServlet {

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
            out.println("<title>Servlet ValiderController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValiderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("huhu");
        try {
            Vente [] ventes = new Vente().select(Connexion.connect());
            request.setAttribute("vente", ventes);
            RequestDispatcher dispatch = request.getRequestDispatcher("listeVente.jsp");
            dispatch.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ArrayList<Panier> paniers =(ArrayList<Panier>)session.getAttribute("panier");
        session.setAttribute("panier", null);
        int idClient = Integer.valueOf(request.getParameter("client"));
        Date dates = Date.valueOf(request.getParameter("date"));
        Vente vente = new Vente(idClient,dates);
        out.println("-----------------------");
        out.println(paniers.size()+"........");
        try {
            vente.insert(Connexion.connect());
            vente = vente.selectLastEnter(Connexion.connect());
            out.println(vente.getIdVente()+"   0121541541");
            Panier[]panie = new Panier().setIdVentePanier(paniers,vente.getIdVente());
            for(int i =0;i<panie.length;i++){
                EtatdeStock etat = new EtatdeStock().etatParPoketra(Connexion.connect(),panie[i].getIdPoketra());
                int valeur = (int)etat.getValeur()- panie[i].getQuantite(); 
                out.println(valeur+"valeur");
                if(valeur<0){
                    out.println(valeur);
                    RequestDispatcher disptatch = request.getRequestDispatcher("erreur.jsp");
                    disptatch.forward(request, response);
                }else{
                    panie[i].insert(Connexion.connect());
                    RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
                     dispatch.forward(request, response);
                }
               
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
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
