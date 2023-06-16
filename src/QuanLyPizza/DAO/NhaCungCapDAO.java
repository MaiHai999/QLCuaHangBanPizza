/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.NHACUNGCAP;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class NhaCungCapDAO {
    
    public static void main(String[] args) {
        List<NHACUNGCAP> list =  getAllNCC();
        for(NHACUNGCAP sp : list){
            //System.out.println(sp.getMaNCC());
        }
    }
    
    public static List<NHACUNGCAP> getAllNCC(){
        List<NHACUNGCAP> listSP = new ArrayList<>(); 
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select * from NhaCungCap");
             // show data
             while (rs.next()){
                NHACUNGCAP sp = new NHACUNGCAP(rs.getString(1), rs.getString(2) );
                listSP.add(sp);
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listSP;
    }
    
}
