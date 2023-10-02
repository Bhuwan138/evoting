<%@page import="evoting.dto.UserDetails"%>
<%@page import="java.util.List"%>
<%@page import="evoting.dao.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Manage User</title>
    <script src="javascript/jquery.js"></script>
    <script src="javascript/adminOptions.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet" />
    <link href="stylesheet/admin.css" rel="stylesheet" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
      crossorigin="anonymous"
      />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
      />
  </head>
  <body>
    <%
      String userId = (String) session.getAttribute("userId");
      if (userId == null) {
        response.sendRedirect("accessdenied.html");
        return;
      }

      UserDAO ud = new UserDAO();
      List<UserDetails> list = ud.getAllUsers();

      StringBuffer displayBlock = new StringBuffer();
      displayBlock.append(""
              + "   <div class ='sticky'>                                               "
              + "   <div class ='candidate' > E-Voting System </div> <br>               "
              + "   <div class='subcandidate'>Admin Actions Page</div> <br><br>         "
              + "   <div class='logout'> <a href='login.html'>logout</a> </div>         "
              + "   </div>");

      out.println(displayBlock);
    %>

    <div class="container">
      <div class="row">
        <div class="col">
          <table class="table table-striped">
            <thead class="sticky">
              <tr>
                <th scope="col">Ctzn_no</th>
                <th scope="col">Username</th>
                <th scope="col">Address</th>
                <th scope="col">City</th>
                <th scope="col">Email</th>
                <th scope="col">Mobile</th>
              </tr>
            </thead>
            <tbody>
              <%
                for(UserDetails u: list){
              %>
              <tr>
                <th scope="row"><%= u.getCtznNo() %></th>
                <td><%= u.getUserName() %></td>
                <td><%= u.getAddress() %></td>
                <td><%= u.getCity() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getMobileNo()  %></td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>


  </body>
</html>
