<%-- 
    Document   : votingResponse
    Created on : Dec 14, 2022, 11:07:36 AM
    Author     : Bhuwan Pandey
--%>

<%@page import="evoting.dto.CandidateInfoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voting Details</title>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
    </head>
    <body>
        <%
            String userId = (String) session.getAttribute("userId");
            if(userId == null){
                response.sendRedirect("accessdenied.html");
                return;
            } 
            CandidateInfoDTO candidate = (CandidateInfoDTO)session.getAttribute("candidate");
            StringBuffer displayBlock = new StringBuffer();
            displayBlock.append(""+
            "   <div class ='sticky'>                                                       "+
            "   <div class ='candidate' > E-Voting System </div> <br> ");
            if(candidate==null){
                displayBlock.append("<div class='subcandidate'>Sorry! Your vote could not be casted. </div> <br><br>");
                displayBlock.append("<div class='logout'> <a href='LoginControllerServlet?logout=logout'>logout</a> </div> ");
                out.println(displayBlock);
            }else{
                displayBlock.append("<div class='subcandidate'>Thank You For Voting </div> <br><br>");
                displayBlock.append("<div class='candidateprofile'>Your Vote added Sucessfully</div>");
                displayBlock.append("<div class='candidateprofile'><p>Candidate Id : "+candidate.getCandidateId()+"</p></div>");
                displayBlock.append("<strong>You voted for </strong><br>"
                + "<img src='data:image/jpg;base64,"+candidate.getSymbol()+"' style='width: 300px; height = 200px;' />");
                
                displayBlock.append("<br/>"
                + "<div class='candidateprofile'>"
                + "     <p>Candidate Id : <strong>"+ candidate.getCandidateId() + "</strong> <br>" 
                + "     <p>Candidate Name : <strong>"+ candidate.getCandidateName() + "</strong> <br>" 
                + "     <p>Party : <strong>"+ candidate.getParty() + "</strong> <br>" 
                + "</div>");
                out.println(displayBlock);
                
            }
        %>
    </body>
</html>
