/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.NHANVIEN;
import QuanLyPizza.Class.PHIEUNHAP;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class PhieuNhapDAO {
    
    public static void main(String[] args) throws ParseException {
        //System.out.println(count());

    }
    
    public static List<PHIEUNHAP> getAllPN(){
        List<PHIEUNHAP> listNV = new ArrayList<>(); 
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select * from ChiTietPhieuNhap");
             // show data
             while (rs.next()){
                PHIEUNHAP nv = new PHIEUNHAP(rs.getString(1), rs.getString(3) , rs.getString(2) ,rs.getDouble(4) , rs.getInt(5) , rs.getDate(6) ,rs.getDouble(7));
               // System.out.println(rs.getDate(6));
               //  System.out.println(rs.getString(1));
                listNV.add(nv);
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNV;
    }
    
    //hàm này dùng để đém số phiếu nhập đã nhập vào dùng để tự động thiết lập mã pn
    public static int count(){
        int max = 0;
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT MaPN FROM ChiTietPhieuNhap");
             // show data
             while (rs.next()){
                 
                 //System.out.println(rs.getString(1));
                 String s = rs.getString(1);
                 int h = Integer.parseInt(s.replace("pn", "").trim());
                 if(max < h){
                     max = h;
                 }else{
                     max = max;
                 }
                 
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return max + 1;
    }
    
    public static void addPN(PHIEUNHAP nv){
            String s1 ,s2,s3;
            double s4, s7;
            int s5;
            Date s6;
            s1 = nv.getMaPN();
            s2 = nv.getMaNCC();
            s3 = nv.getMaSP();
            s4 = nv.getGiaCungCap();
            s5 = nv.getSoLuongNhap();
            s6 = nv.getNgayNhap();
            s7 = s4 * s5;
            String s6_1 = new SimpleDateFormat("yyyy-MM-dd").format(s6);
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             //System.out.println("INSERT INTO ChiTietPhieuNhap "
             //        + "VALUES( '" +s1 + "','" +s2 + "','" + s3 +"', " + s4 + ", " + s5 + ", '"+ s6_1 + "'," + s7 +"  )");
             stmt.executeUpdate("INSERT INTO ChiTietPhieuNhap "
                     + "VALUES( '" +s1 + "','" +s2 + "','" + s3 +"', " + s4 + ", " + s5 + ", '"+ s6_1 + "'," + s7 +"  )" );
             
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //hàm này dùng để truy lấy các row cùng mã phiếu nhập
    public static List<PHIEUNHAP> getAllPNKey(String key){
        List<PHIEUNHAP> listNV = new ArrayList<>(); 
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select * from ChiTietPhieuNhap WHERE MaPN = '" + key + "'");
             // show data
             while (rs.next()){
                PHIEUNHAP nv = new PHIEUNHAP(rs.getString(1), rs.getString(3) , rs.getString(2) ,rs.getDouble(4) , rs.getInt(5) , rs.getDate(6) ,rs.getDouble(7));
              
                listNV.add(nv);
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNV;
    }
    
    //hàm này dùng để xóa
    public static void deletePN(String key1 , String key2 , String key3){
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             //System.out.println("DELETE FROM ChiTietPhieuNhap where MaPN = '"+ key1 +"' AND MaNhaCungCap = '" + key2 + "' AND MaSP = '" + key3 + "'");
             stmt.executeUpdate("DELETE FROM ChiTietPhieuNhap where MaPN = '"+ key1 +"' AND MaNhaCungCap = '" + key2 + "' AND MaSP = '" + key3 + "'");
             // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //hàm này dùng để cập nhật
    public static void updatePN(PHIEUNHAP nv , String key1 , String key2 , String key3){
        String s1 ,s2;
        s1 = nv.getMaNCC();
        s2 = nv.getMaSP();
        double s3 = nv.getGiaCungCap();
        double s4 = nv.getSoLuongNhap();
        double s5 = s3 * s4;
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             //System.out.println("UPDATE ChiTietPhieuNhap SET MaNhaCungCap = '" + s1.trim() + "' , MaSP = '" + s2.trim() + "', GiaCungCap = "+ s3 +" , SoLuongNhap = " +s4 +" , ThanhTien = "+s5 +" WHERE MaNhaCungCap = '"+ key2.trim() + "' AND MaSP = '"+key3.trim()+"' AND MaPN = '" +key1.trim()+"'");
             stmt.executeUpdate("UPDATE ChiTietPhieuNhap SET MaNhaCungCap = '" + s1.trim() + "' , MaSP = '" + s2.trim() + "', GiaCungCap = "+ s3 +" , SoLuongNhap = " +s4 +" , ThanhTien = "+s5 +" WHERE MaNhaCungCap = '"+ key2.trim() + "' AND MaSP = '"+key3.trim()+"' AND MaPN = '" +key1.trim()+"'");
             // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //hàm này dùng xem tất cả các mã pn khác nhạu
    public static List<PHIEUNHAP> getAllPNDist(){
        List<PHIEUNHAP> listNV = new ArrayList<>(); 
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select DISTINCT MaPN , NgayNhap from ChiTietPhieuNhap");
             // show data
             while (rs.next()){
                PHIEUNHAP nv = new PHIEUNHAP();
                nv.setMaPN(rs.getString(1));
                Date NgayNhap = rs.getDate(2);
                String date = new SimpleDateFormat("dd/MM/yyyy").format(NgayNhap);
                nv.setNgayNhap(date);
    
                //System.out.println(rs.getString(1));
                // System.out.println(date);
                 
                listNV.add(nv);
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listNV;
    }
    
    //hàm này dùng để tìm theo ngày tháng
    public static List<PHIEUNHAP> getAllPNDistSearch(String s){
        List<PHIEUNHAP> listNV = new ArrayList<>(); 
        List<PHIEUNHAP> listNV1 = new ArrayList<>(); 
        listNV = getAllPNDist();
        for(PHIEUNHAP pn : listNV){
            String date = new SimpleDateFormat("dd/MM/yyyy").format(pn.getNgayNhap());
            if(date.trim().contains(s.trim())){
                listNV1.add(pn);
            }
        }
        return listNV1;

    }
 
    
    
}
