/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;


import QuanLyPizza.Class.LOAI;
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
public class LoaiDAO {
    
    public static void main(String[] args) {
        List<LOAI> list =  getAllLoai();
        for(LOAI sp : list){
            //System.out.println(sp.getMaLoai());
        }
    }
    
    //hàm này dừng để quét toàn bộ dữ liệu từ database vào list
    public static List<LOAI> getAllLoai(){
        List<LOAI> listSP = new ArrayList<>(); 
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select * from Loai");
             // show data
             while (rs.next()){
                LOAI sp = new LOAI(rs.getString(1), rs.getString(2) );
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
    
    
    
