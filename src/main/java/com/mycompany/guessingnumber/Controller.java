package com.mycompany.guessingnumber;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Controller extends HttpServlet {

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
        
        String action = request.getParameter("action");
        
        if (action != null) {
            
            if ("Connexion".equals(action)) {
                
                //Initialisation du nom pour la session
                String playerName = request.getParameter("playerName");
                request.getSession(true).setAttribute("playerName",playerName);
                
                //Initialisation du nombre de tentative pour la session
                request.getSession(true).setAttribute("tentative",1);
                
                //Initialisation du meilleur score pour la session
                BestScore BS = new BestScore("personne",0);
                request.getSession(true).setAttribute("BS",BS);
                
                //Initialisation de la réponse à trouver dans la session
                Random r = new Random();
                int rp = r.nextInt(101);
                request.getSession(true).setAttribute("rp",rp);
                
                request.getRequestDispatcher("game.jsp").forward(request,response);
                
            } else if ("Deviner".equals(action)) {
                
                //On récupère la réponse et le nombre de tentative
                int rp = (int) request.getSession().getAttribute("rp");
                int tentative = (int) request.getSession().getAttribute("tentative");
                
                //On récupère la proposition
                String guess = request.getParameter("guess");
                int prop = Integer.parseInt(guess);
                request.setAttribute("prop", prop);
               
                String ms = "";
                
                if (rp < prop) {
                    ms = ": Plus bas";
                    request.setAttribute("ms", ms);
                    request.getSession(true).setAttribute("tentative",tentative+1);
                    request.getRequestDispatcher("game.jsp").forward(request,response);
                } else if (rp > prop) {
                    ms = ": Plus haut";
                    request.setAttribute("ms", ms);
                    request.getSession(true).setAttribute("tentative",tentative+1);
                    request.getRequestDispatcher("game.jsp").forward(request,response);
                } else if (rp == prop) {
                    BestScore BS = (BestScore) request.getSession().getAttribute("BS");
                    if (tentative < BS.getScore() || BS.getScore()==0){
                        BestScore NewBS = new BestScore((String) request.getSession().getAttribute("playerName"),tentative);
                        System.out.println(NewBS.getScore());
                        request.getSession(true).setAttribute("BS",NewBS);
                    }
                    request.getRequestDispatcher("end.jsp").forward(request,response);
                }
                
            } else if ("Deconnexion".equals(action)) {
                
                //On ferme la session
                request.getSession(true).invalidate();
                request.getRequestDispatcher("begin.jsp").forward(request,response);
                
            } else if ("Rejouer".equals(action)) {
                
                //On réinitialise la tentative à 1
                request.getSession(true).setAttribute("tentative",1);
                
                //On crée une nouvelle réponse
                Random r = new Random();
                int rp = r.nextInt(101);
                request.getSession(true).setAttribute("rp",rp);
                
                request.getRequestDispatcher("game.jsp").forward(request,response);
                
            }
          
        } else {
            request.getRequestDispatcher("begin.jsp").forward(request,response);
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
