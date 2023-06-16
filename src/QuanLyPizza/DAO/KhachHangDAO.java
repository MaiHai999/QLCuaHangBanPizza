/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.KHACHHANG;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class KhachHangDAO {

    public static List<KHACHHANG> getAllKH() {
        List<KHACHHANG> listKH = new ArrayList<>();
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            ResultSet rs = stmt.executeQuery("select * from KhachHang");
            // show data
            while (rs.next()) {
                KHACHHANG kh = new KHACHHANG(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                //System.out.println("");
                listKH.add(kh);
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listKH;
    }

    // tìm khach hàng by key
    public static List<KHACHHANG> searchKH(ArrayList<KHACHHANG> listKH, String key) {
        List<KHACHHANG> ListKH2 = new ArrayList<>();
        for (KHACHHANG kh : listKH) {
            if (kh.getTenKH().trim().contains(key)) {
                ListKH2.add(kh);
                //System.out.println(kh.getMaKH() + "                  nằm trong cái đnag ti");
            }
        }
        return ListKH2;
    }

    // tim khach hang voi TongChiTieu (min Max)
    public static List<KHACHHANG> searchKHChiTieu(ArrayList<KHACHHANG> listKH, Double min, Double max) {

        List<KHACHHANG> ListKH1 = new ArrayList<>();

        for (KHACHHANG kh : listKH) {
            //if(kh.getTongChiTieu()>=min &&kh.getTongChiTieu()<=max){
            if (kh.getTongChiTieu() >= min && kh.getTongChiTieu() <= max) {
                ListKH1.add(kh);
                //System.out.println(kh.getMaKH() + "            cái này ở hàm daio ádlkndflkndlndsfklasdnfklsdnf");
            }
        }
        return ListKH1;
    }

    // them khach hang
    public static void addKH(KHACHHANG kh) {
        String s1, s2, s3;
        Double s4;
        s1 = kh.getMaKH();
        s2 = kh.getTenKH();
        s3 = kh.getGioiTinh();
        s4 = kh.getTongChiTieu();
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
//            System.out.println("INSERT INTO KhachHang (MaKH,Ten,GioiTinh,TongChiTieu)"
//                    + "VALUES( '" + s1 + "','" + s2 + "','" + s3 + "', " + s4 + " )");

            stmt.executeUpdate("INSERT INTO KhachHang (MaKH,Ten,GioiTinh,TongChiTieu)"
                    + "VALUES( '" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + " ')");

            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void updateKH(KHACHHANG kh, String key) {
        String s1, s2, s3;
        Double s4;
        s1 = kh.getMaKH();
        s2 = kh.getTenKH();
        s3 = kh.getGioiTinh();
        s4 = kh.getTongChiTieu();

        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            //System.out.println();
            stmt.executeUpdate(" UPDATE KhachHang SET MaKH = '" + s1 + "', Ten = '" + s2 + "' , GioiTinh = ' " + s3
                    + "',TongChiTieu ='" + s4 + "'WHERE MaKH = '" + key + "'");
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // xoa 1 khach hang

    public static void deleteKH(String key) {
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            stmt.executeUpdate("DELETE FROM KhachHang where MaKH = '" + key + "'");
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String coutKH() {
        String query = "SELECT count(MaKH) FROM KhachHang";
        String s = "";
        try {
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                s = rs.getString(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    //hàm này dùng để tự cập nhật tổng tiền chi tiêu
    public static void AotuUpdateKH(Double s, String key) {
        
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            //System.out.println();
            stmt.executeUpdate(" UPDATE KhachHang SET TongChiTieu ='" + s + "'WHERE MaKH = '" + key + "'");
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static double getTKHKey(String key){
        double s = 0;
        try{
            
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select TongChiTieu from KhachHang WHERE MaKH = '" + key +"'");
             // show data
             while (rs.next()){
                s = rs.getDouble(1);
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
        //System.out.println(coutKH());
//        AotuUpdateKH(30000.0 , "kh01");

//        KHACHHANG kh = new KHACHHANG("kh07", "LOi", "Nam", 20000);
//        //addKH(kh);
//        //updateKH(kh,"kh06");
//        //deleteKH("kh07");
//        List<KHACHHANG> ListKH1 = getAllKH();
//        List<KHACHHANG> ListKH2 = new ArrayList<>();
//        ListKH2 = searchKHChiTieu((ArrayList<KHACHHANG>) ListKH1, 1.0, 4000000.0);
//        for(KHACHHANG kh1 : ListKH2){
//            System.out.println(kh1.getMaKH() );
//        }
    }

}
