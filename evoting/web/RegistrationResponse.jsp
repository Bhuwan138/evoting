<%-- 
    Document   : RegistrationResponse
    Created on : Nov 17, 2022, 3:14:35 PM
    Author     : Bhuwan Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean result = (boolean)request.getAttribute("result");
    boolean isUserFound = (boolean)request.getAttribute("isUserFound");
    String userName = (String)request.getAttribute("userName");
    
    if(isUserFound) out.println("uap");
    else if(result) out.println("success");
    else out.println("error");
%>
