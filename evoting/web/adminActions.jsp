<%-- 
    Document   : adminActions
    Created on : Nov 19, 2022, 5:03:27 PM
    Author     : Bhuwan Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="javascript/adminOptions.js"></script>
        <script src="javascript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <title>Admin Action Page</title>
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
                "<div id='dv1' onclick='manageUser()'>  <img src='images/muser.png'  height='255px' width='255px'> <br> <h3>Users Options</h3> </div>                   "+
                "<div id='dv2' onclick='manageCandidate()'>  <img src='images/ManageCandLists.jpg'  height='255px' width='255px'> <br> <h3>Admin Options</h3> </div>    "+
                "<div id='dv3' onclick='electionResult()'>  <img src='images/resultgraph.jpg'  height='255px' width='255px'> <br> <h3>Result Options</h3> </div>         "+
                " <br><br> <div id='result' align='center'></div> </div>");
            out.println(displayBlock);
        %>
    </body>
</html>
