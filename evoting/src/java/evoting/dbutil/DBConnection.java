/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bhuwan Pandey
 */
public class DBConnection {
    private static Connection conn = null;
    static{
        try{
            //loading driver
            Class.forName("oracle.jdbc.OracleDriver");
            
            //getting driver connection
            String url = "jdbc:oracle:thin:@//localhost:1521/xe";
            String userName = "evoting_db";
            String password = "evoting";
            conn = DriverManager.getConnection(url,userName,password);
            System.out.println("User connected Sucessfully");
        }catch(ClassNotFoundException | SQLException exception){
            exception.printStackTrace();
        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
    
    public static void closeConnection(){
        try{
            if(conn != null){
                conn.close();
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}
