/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 * Bhuwan
 * Pandey
 */
public class UserDAO {

  private static PreparedStatement psForSearch, psGetAllUsers;

  static {
    try {
      psForSearch = DBConnection.getConnection().prepareStatement("SELECT user_type FROM user_details where ctzn_no = ? and password = ?");
      psGetAllUsers = DBConnection.getConnection().prepareStatement("SELECT * FROM user_details where user_type ='Voter' ");
    } catch (SQLException se) {
      se.printStackTrace();
    }
  }

  public static String validateUser(UserDTO user) throws SQLException {
    psForSearch.setString(1, user.getUserId());
    psForSearch.setString(2, user.getPassword());
    ResultSet rs = psForSearch.executeQuery();
    if (rs.next()) {
      return rs.getString(1);
    }
    return null;
  }

  public List<UserDetails> getAllUsers() throws SQLException {
    ResultSet rs = psGetAllUsers.executeQuery();
    List<UserDetails> list = new ArrayList<>();
    while (rs.next()) {
      UserDetails ud = new UserDetails();
      ud.setCtznNo(rs.getString(1));
      ud.setUserName(rs.getString(3));
      ud.setAddress(rs.getString(4));
      ud.setCity(rs.getString(5));
      ud.setEmail(rs.getString(6));
      ud.setMobileNo(rs.getString(7));
      list.add(ud);
    }

    return list;
  }

}
