/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.TAIKHOAN;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class TaiKhoanDAO {
    
    
    public static List<TAIKHOAN> dnTK(String username, String password) {
        List<TAIKHOAN> listTK = new ArrayList<>();
        String query = "SELECT * FROM TaiKhoan where Login='" + username + "' and PassWord='" + password + "'";
        try {
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                TAIKHOAN tk = new TAIKHOAN(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //System.out.println();
                //System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                listTK.add(tk);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTK;

    }
    
    
    public static List<TAIKHOAN> getAllTK() {
        List<TAIKHOAN> listTK = new ArrayList<>();
        String query = "SELECT * FROM TaiKhoan ";
        try {
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                TAIKHOAN tk = new TAIKHOAN(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                listTK.add(tk);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTK;

    }
    
    
    //hàm này dùng để xem sự tồn tại của password và login
    public boolean existTK(String login ,String password){
        List<TAIKHOAN> listTK = new ArrayList<>();
        for(TAIKHOAN tk : listTK){
//            System.out.println(tk.getTEN_TAI_KHOAN().trim());
//            System.out.println(login.trim());
//            System.out.println(tk.getMAT_KHAU().trim());
//            System.out.println(password.trim());
            if(tk.getTEN_TAI_KHOAN().trim().equals(login.trim()) & tk.getMAT_KHAU().trim().equals(password.trim())){
                return true;
            }
        }
        return false;
    }
    
    //hàm chèn tài khoản trả về một list tài khoản
    public static List<TAIKHOAN> dkTK(String MATK, String username, String password, String MaNV) throws SQLException {
        List<TAIKHOAN> listTK = new ArrayList<>();
        String query = "insert into TaiKhoan(MaTK , Login , PassWord , MaNV) values ('" + MATK + "','" + username + "','" + password + "','" + MaNV + "')";
        try {
            Connection con = getConnection();
            Statement stm = con.createStatement();
            int x = stm.executeUpdate(query);
            if (x == 0) {
                System.out.println("tai khoan ko ton tai");
            } else {
                System.out.println("dangw ki thanh cong");

            }
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTK;

    }
    
    //hàm update tài khoản
    public static void updateTK(TAIKHOAN sp , String key){
            String s1 ,s2,s3,s4;
            s1 = sp.getMaTK();
            s2 = sp.getTEN_TAI_KHOAN();
            s3 = sp.getMAT_KHAU();
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             //System.out.println("UPDATE TaiKhoan SET MaTK = '" + s1 + "', Login = '" +s2 +"' , PassWord = '"+ s3 +"' WHERE MaNV = '"+ key +"'");
             stmt.executeUpdate("UPDATE TaiKhoan SET MaTK = '" + s1 + "', Login = '" +s2 +"' , PassWord = '"+ s3 +"' WHERE MaNV = '"+ key +"'" );
             // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //hàm chèn tài khoản trả về void
    public static void addTK(TAIKHOAN tk) throws SQLException {
        List<TAIKHOAN> listTK = new ArrayList<>();
        String query = "insert into TaiKhoan(MaTK , Login , PassWord , MaNV) values ('" + tk.getMaTK() + "','" + tk.getTEN_TAI_KHOAN() + "','" + tk.getMAT_KHAU() + "','" + tk.getMaNV() + "')";
        try {
            Connection con = getConnection();
            Statement stm = con.createStatement();
            int x = stm.executeUpdate(query);
            if (x == 0) {
                System.out.println("tai khoan ko ton tai");
            } else {
                System.out.println("dangw ki thanh cong");

            }
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    //hàm xóa tài khoản
    public static void deleteTK(String key){
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             stmt.executeUpdate("DELETE FROM TaiKhoan where MaNV = '"+ key +"'" );
             // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //hàm tìm kiếm mã nhân viên
    public static TAIKHOAN searchTK(String MaNV){
        List<TAIKHOAN> listTK = getAllTK();
        TAIKHOAN etk = new TAIKHOAN();
        for(TAIKHOAN tk : listTK){
            if(tk.getMaNV().trim().equals(MaNV.trim())){
                return tk;
            }
        }
        return etk;
    } 
 
 
    
    public static void main(String[] args) throws SQLException {
//        TAIKHOAN tk = new TAIKHOAN("tk03", "thao vy", "99999", "nv04");
//        deleteTK("nv04");
//        getAllTK();
        searchTK("nv01");
 
    }
    
    
}
