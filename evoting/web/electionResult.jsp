<%-- 
    Document   : electionResult
    Created on : Dec 14, 2022, 12:36:35 PM
    Author     : Bhuwan Pandey
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="evoting.dto.CandidateDetailsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userId = (String) session.getAttribute("userId");
    if(userId == null){
        response.sendRedirect("accessdenied.html");
        return;
    }
    
    Map<CandidateDetailsDTO,Integer> resultDetails = (Map<CandidateDetailsDTO,Integer>)request.getAttribute("result");
    int countVote = (int)request.getAttribute("voteCount");
    Iterator it = resultDetails.entrySet().iterator();
    StringBuffer displayBlock = new StringBuffer();
    displayBlock.append("<table>");
    displayBlock.append("<tr>"
            + "<th>Candidate Id</th>"
            + "<th>Candidate Name</th>"
            + "<th>Party</th>"
            + "<th>Symbol</th>"
            + "<th>City</th>"
            + "<th>Vote Count</th>"
            + "<th>Vote Percentage</th>"
            + "</tr>");
    while(it.hasNext()){
        Map.Entry<CandidateDetailsDTO,Integer> e = (Map.Entry)it.next();
        CandidateDetailsDTO cd = e.getKey();
        float votePercentage = (e.getValue()*100.0F)/countVote; 
        displayBlock.append("<tr>"
            + "<td>" + cd.getCandidateId() + "</td>"
            + "<td>" + cd.getCandidateName() + "</td>"
            + "<td>" + cd.getParty() + "</td>"
            + "<td><img src='data:image/jpg;base64,"+cd.getSymbol()+"' style='width: 300px; height = 200px;' /></td>"
            + "<td>" + cd.getCity() + "</td>"
            + "<td>" + e.getValue() + "</td>"
            + "<td>" + cd.getCandidateId() + "</td>"
            + "<td>" + votePercentage + "</td>"
            + "</tr>");
    }
    displayBlock.append("</table>");
    out.println(displayBlock);
%>
