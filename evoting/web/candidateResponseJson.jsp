<%-- 
    Document   : candidateResponseJson
    Created on : Nov 25, 2022, 7:02:56 PM
    Author     : Bhuwan Pandey
--%>

<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    JSONObject json = (JSONObject)request.getAttribute("json");
    String userId = (String) session.getAttribute("userId");
    if(userId == null){
        response.sendRedirect("accessdenied.html");
        return;
    }
    
    if(json != null){
            out.println(json);
    }
    
%>