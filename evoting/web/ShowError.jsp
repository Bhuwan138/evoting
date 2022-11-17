<%-- 
    Document   : ShowError
    Created on : Nov 17, 2022, 3:21:03 PM
    Author     : Bhuwan Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Exception exp = (Exception)request.getAttribute("errorPage");
    System.out.println("Exception is :  " + exp);
    out.println("Some Exception occurred : " + exp.getMessage());
    
%>
