<%-- 
    Document   : LoginResponse
    Created on : Nov 19, 2022, 2:42:49 PM
    Author     : Bhuwan Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userId = (String)request.getAttribute("userId");
    String result = (String)request.getAttribute("result");
    System.out.println("userId :" + userId + " \n result :" + result);
        if(userId!=null && result!=null){
            HttpSession sess=request.getSession();
            sess.setAttribute("userId", userId);
            String url = "";
            if(result.equalsIgnoreCase("Admin")){
                url="AdminControllerServlet;jsessionid="+session.getId();
            }
            else{
                url="VotingControllerServlet;jsessionid="+session.getId();
            }
            out.println(url);
        }
        else{
            out.println("error");
        }
%>
