
package evoting.controller;

import evoting.dao.CandidateDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Bhuwan Pandey
 */
public class AddCandidateControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
       HttpSession sess = request.getSession();
       RequestDispatcher rd = null;
       String userId = (String) sess.getAttribute("userId");
       if(userId == null){
           sess.invalidate();
           response.sendRedirect("accessdenied.html");
       }
       String candidate = request.getParameter("id");
               
       String userIdForName =request.getParameter("uid");
       if(candidate != null && candidate.equalsIgnoreCase("getId")){
           try{
               rd = request.getRequestDispatcher("candidateResponse.jsp");
               String id = CandidateDAO.getNewId();
               request.setAttribute("id", id);
                
           }catch(SQLException ex){
                ex.printStackTrace();
                rd = request.getRequestDispatcher("ShowError.jsp");
                request.setAttribute("errorPage", ex);    
            }
           finally{
                if(rd != null){
                 rd.forward(request, response);
                }
            }
       }else if(userIdForName!= null){
           try{
               rd = request.getRequestDispatcher("candidateResponseJson.jsp");
               String userName = CandidateDAO.getUserNameById(userIdForName);
               List<String> cityList = CandidateDAO.getCities();
               JSONObject json = new JSONObject();
               StringBuffer sb = new StringBuffer();
               
               for(String city : cityList){
                   sb.append("<option value=' ").append(city).append("  '> ").append(city).append("</option>");
               }
               
               if(userName == null) {
                   userName = "wrong";
               }
               
               json.put("userName", userName);
               json.put("city", sb.toString());
               request.setAttribute("json", json);
               
               
           }catch(Exception ex){
                ex.printStackTrace();
                rd = request.getRequestDispatcher("ShowError.jsp");
                request.setAttribute("errorPage", ex);   
            }
           finally{
                if(rd != null){
                 rd.forward(request, response);
                }
            }
       }else{
           
            System.out.println("candidate : " + candidate + " userid : " + userIdForName);
           
           
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
