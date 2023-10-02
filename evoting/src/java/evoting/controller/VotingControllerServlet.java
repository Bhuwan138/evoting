/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dao.VoteDAO;
import evoting.dto.CandidateInfoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bhuwan Pandey
 */
public class VotingControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = null;
        HttpSession sess = request.getSession();
        String userId = (String) sess.getAttribute("userId");
        if(userId == null){
           sess.invalidate();
           response.sendRedirect("accessdenied.html");
        }
        
        try{
            String candidateId = VoteDAO.getCandidateId(userId);
            
            
//            if not vote
            if(candidateId == null){
                List<CandidateInfoDTO> candidateList = CandidateDAO.viewCandiate(userId);
                request.setAttribute("candidateList", candidateList);
                rd = request.getRequestDispatcher("showCandidate.jsp");
            }else{
                CandidateInfoDTO candidate = VoteDAO.getVote(candidateId);
                request.setAttribute("candidate", candidate);
                rd = request.getRequestDispatcher("voteDenied.jsp");  
            }
        }catch(Exception ex){
            ex.printStackTrace();
            request.setAttribute("errorPage", ex);
            rd = request.getRequestDispatcher("ShowError.jsp");
        }
        
        finally{
            rd.forward(request, response);
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
