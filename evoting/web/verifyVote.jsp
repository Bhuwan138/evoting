<%
    String userId = (String) session.getAttribute("userId");
    if(userId == null){
        response.sendRedirect("accessdenied.html");
        return;
    } 
    
    boolean result =  (boolean)request.getAttribute("result");
    if(result){
        out.println("success");
    }else{
        out.println("failed");
    }
%>
