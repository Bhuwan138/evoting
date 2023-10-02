<%-- 
    Document   : ShowCandidate
    Created on : Dec 10, 2022, 9:27:06 AM
    Author     : Bhuwan Pandey
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="evoting.dto.CandidateDetailsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userId = (String)session.getAttribute("userId");
    StringBuffer displayBlock = new StringBuffer();
    if(userId== null){
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result = (String)request.getAttribute("result");
    JSONObject json = new JSONObject();
    if(result != null && result.equalsIgnoreCase("candidateList")){
        displayBlock.append("<option value=''> Choose ID </option>");
        List<String> candidateIdList = (List<String>)request.getAttribute("candidateId");
        for(String candidate: candidateIdList){
            displayBlock.append("<option value='"+candidate+"'> "+ candidate +" </option>");
        }
        json.put("cid", displayBlock.toString());
        System.out.println("In AdminShowCandidate if part : "+ displayBlock);
    }else if(result != null && result.equalsIgnoreCase("candidateDetails")){
        CandidateDetailsDTO candidateDetails = (CandidateDetailsDTO)request.getAttribute("candidateDetails");
        String img = "<img src = 'data:image/jpg;base64,"+candidateDetails.getSymbol()+"' style='width:300px;height:200px' />";
        displayBlock.append("<table border=1>");
        displayBlock.append("<tr>  <th>User Id</th>  <td>"+candidateDetails.getUserId()+"</td>  </tr>");
        displayBlock.append("<tr>  <th>Candidate name</th>  <td>"+candidateDetails.getCandidateName()+"</td>  </tr>");
        displayBlock.append("<tr>  <th>Party</th>  <td>"+candidateDetails.getParty()+"</td>  </tr>");
        displayBlock.append("<tr>  <th>City</th>  <td>"+candidateDetails.getCity()+"</td>  </tr>");
        displayBlock.append("<tr>  <th>Symbol</th>  <td>"+img+"</td>  </tr>");
        displayBlock.append("</table>");
        json.put("subDetails", displayBlock.toString());
        System.out.println("In AdminShowCandidate else part: "+ displayBlock);
    }
    out.println(json);
    
%>