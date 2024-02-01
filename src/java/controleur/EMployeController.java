
package controleur;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Connexion;
import model.Employe;
import model.Personne;

/**
 *
 * @author Tsiky Aro
 */
public class EMployeController extends HttpServlet {

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
            out.println("<title>Servlet EMployeController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EMployeController at " + request.getContextPath() + "</h1>");
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
            Personne [] personne = new Personne().select(Connexion.connect());
            request.setAttribute("personnes", personne);
            RequestDispatcher dispatch = request.getRequestDispatcher("EMploye.jsp");
            dispatch.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPersonne = Integer.valueOf(request.getParameter("personne"));
        Date date = Date.valueOf(request.getParameter("date"));
        try {
            new Employe(idPersonne,date).insert(Connexion.connect());
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
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
