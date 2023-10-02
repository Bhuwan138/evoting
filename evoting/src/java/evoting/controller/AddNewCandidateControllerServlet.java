/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Bhuwan Pandey
 */
public class AddNewCandidateControllerServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      RequestDispatcher rd = null;
      try{
          DiskFileItemFactory df = new DiskFileItemFactory();
          ServletFileUpload sfu = new ServletFileUpload(df);
          ServletRequestContext ctq = new ServletRequestContext(request);
          List<FileItem> multiList = sfu.parseRequest(ctq);
          List<String> objValues = new ArrayList<>();
          InputStream imp = null;
          for(FileItem file: multiList){
              if(file.isFormField()){
                  String fieldName = file.getFieldName();
                  String value = file.getString();
                  System.out.println(fieldName + " : " + value);
                  objValues.add(value);
              }else{
                  imp =file.getInputStream();
                  String key = file.getFieldName();
                  String fileName = file.getName();
                  System.out.println("Inside else");
                  System.out.println(key + " : " + fileName);
                  
              }
          }
          
          for(String list: objValues){
              System.out.println("--> " + list);
          }
          
          CandidateDTO candidate = new CandidateDTO();
          candidate.setCandidateId(objValues.get(2));
          candidate.setCity(objValues.get(1));
          candidate.setParty(objValues.get(5));
          candidate.setUserId(objValues.get(6));
          candidate.setSymbol(imp);
          boolean result = CandidateDAO.addCandidate(candidate);
          if(result){
              rd = request.getRequestDispatcher("success.jsp");
          }else{
              rd = request.getRequestDispatcher("failure.jsp");
          }
      }catch(Exception e){
          System.out.println("Exception in AddnewCandidateControllerServlet : " + e);
      }
      
      finally{
          if(rd!=null){
              rd.forward(request, response);
          }
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
