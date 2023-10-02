<%-- 
    Document   : manageCandidate
    Created on : Nov 19, 2022, 5:18:38 PM
    Author     : Bhuwan Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Candidate</title>
        <script src="javascript/jquery.js"></script>
         <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> 
         <script src="javascript/adminOptions.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
    </head>
    <body>
        <%
            String userId = (String) session.getAttribute("userId");
            if(userId == null){
                response.sendRedirect("accessdenied.html");
                return;
            }
            StringBuffer displayBlock = new StringBuffer();
            displayBlock.append(""+
            "   <div class ='sticky'>                                               "+
            "   <div class ='candidate' > E-Voting System </div> <br>               "+
            "   <div class='subcandidate'>Admin Actions Page</div> <br><br>         "+
            "   <div class='logout'> <a href='login.html'>logout</a> </div>         "+
            "   </div>");
            
            displayBlock.append(""+
                "<div class='container'> "+
                "<div id='dv1' onclick='showAddCandidateForm()'>  <img src='images/addcandidate.png'  height='255px' width='255px'> <br> <h3>Add Candidate</h3> </div>                   "+
                "<div class='mx-5' id='dv3' onclick='showCandidate()'>  <img src='images/candidate.jpg'  height='255px' width='255px'> <br> <h3>Show Candidate</h3> </div>         "+
                 " </div>");
            displayBlock.append("<br><br><div id='result' align='center'></div>");
            out.println(displayBlock);
        %>
    </body>
</html>
