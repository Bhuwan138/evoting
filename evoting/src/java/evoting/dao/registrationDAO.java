/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Bhuwan Pandey
 */
public class RegistrationDAO {
    private static PreparedStatement psForSearch,psForInsert;
    static{
        try{
            psForSearch = DBConnection.getConnection().prepareStatement("SELECT * FROM user_details where ctzn_no = ?");
            psForInsert = DBConnection.getConnection().prepareStatement("INSERT INTO user_details values(?,?,?,?,?,?,?,?)");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    public static boolean searchUser(String userId) throws SQLException{
        psForSearch.setString(1, userId);
        return psForSearch.executeQuery().next(); //returning ResultSet value
    }
    
    public static boolean registerUser(UserDetails users)throws SQLException{
        psForInsert.setString(1, users.getCtznNo());
        psForInsert.setString(2, users.getPassword());
        psForInsert.setString(3, users.getUserName());
        psForInsert.setString(4, users.getAddress());
        psForInsert.setString(5, users.getCity());
        psForInsert.setString(6, users.getEmail());
        psForInsert.setString(7, users.getMobileNo());
        psForInsert.setString(8, "Voter");
        return psForInsert.executeUpdate()==1;
    }
    
    
}
