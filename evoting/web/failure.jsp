<%
    String userId = (String)session.getAttribute("userId");
    if(userId == null){
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    out.println("failed");
%>