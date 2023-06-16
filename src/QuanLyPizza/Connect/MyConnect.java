/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Connect;

/**
 *
 * @author HP
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyConnect {
    
    public static void main(String[] args) {
        getConnection();
     
    }
    
    public static Connection getConnection() {
        String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=QL_Pizza;"
             + ";encrypt=true;trustServerCertificate=true;";
        String USER_NAME = "sa1";
        String PASSWORD = "12345";
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static void print1(){
        System.out.println("cc");
    }

   
        
}
