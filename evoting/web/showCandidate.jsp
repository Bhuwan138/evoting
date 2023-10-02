
<%@page import="java.util.ArrayList"%>
<%@page import="evoting.dto.CandidateInfoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Candidate</title>
        <script src="javascript/jquery.js"></script>
        <script src="javascript/vote.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
    </head>
    <body>
        <%
            String userId = (String) session.getAttribute("userId");
            userId = userId.trim();
            if(userId == null){
                response.sendRedirect("accessdenied.html");
                return;
            }
            StringBuffer displayBlock = new StringBuffer();
            displayBlock.append(""+
            "   <div class ='sticky'>                                                       "+
            "   <div class ='candidate' > E-Voting System </div> <br>                       "+
            "   <div class='subcandidate'>Whom do you want to vote?</div> <br><br>          "+
            "   <div class='logout'> <a href='login.html'>logout</a> </div>                 "+
            "   </div>");
            
            displayBlock.append(""
            +"<div class='buttons'> "
            );
            List<CandidateInfoDTO> candidateList = (List)request.getAttribute("candidateList");
            for(CandidateInfoDTO c: candidateList){
                displayBlock.append("<div class='listGrps'>");
                displayBlock.append("<input hidden type='radio' name='flat' id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' onclick='addVote()'>");
                displayBlock.append("<label for='"+c.getCandidateId()+"'> <img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width: 300px; height = 200px;' /> <label>");
                displayBlock.append("<br/>"
                + "<div class='candidateprofile'>"
                + "     <p>Candidate Id : <strong>"+ c.getCandidateId() + "</strong> <br>" 
                + "     <p>Candidate Name : <strong>"+ c.getCandidateName() + "</strong> <br>" 
                + "     <p>Party : <strong>"+ c.getParty() + "</strong> <br>" 
                + "</div>"
                + "</div>");
            }
            
            out.println(displayBlock);
        %>
    </body>
</html>
