<%-- 
    Document   : candidateResponse
    Created on : Nov 22, 2022, 11:32:17 PM
    Author     : Bhuwan Pandey
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String id = (String)request.getAttribute("id");
    String userId = (String) session.getAttribute("userId");
    if(userId == null){
        response.sendRedirect("accessdenied.html");
        return;
    }
    
    if(id != null){
        out.println(id);
    }
    
    
%>