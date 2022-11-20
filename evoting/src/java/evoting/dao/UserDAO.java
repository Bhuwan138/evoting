/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bhuwan Pandey
 */
public class UserDAO {
    private static PreparedStatement psForSearch;
    static{
        try{
            psForSearch = DBConnection.getConnection().prepareStatement("SELECT user_type FROM user_details where ctzn_no = ? and password = ?");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    public static String validateUser(UserDTO user) throws SQLException {
        psForSearch.setString(1,user.getUserId());
        psForSearch.setString(2,user.getPassword());
        ResultSet rs=psForSearch.executeQuery();
        if(rs.next()) {
            System.out.println("Getting user type. : " + rs.getString(1));
            return rs.getString(1);
        }
      return null;
    }
    
    
}

