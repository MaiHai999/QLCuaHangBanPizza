/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.KHUYENMAI;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class KhuyenMaiDAO {

    //hàm này để lấy mã khuyến mãi và phần trăm khuyến mãi
    public static List<String> showCOMBOBOX_IN_HOA_DON() throws SQLException {
        List<String> list = new ArrayList<>();
        try {
            String query = "SELECT MaKM , PhanTramKhuyenMai FROM KhuyenMai";
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {

                String l = rs.getString(2);
                list.add(l);
                //System.out.println(l);
            }

        } catch (SQLException e) {
        }
        return list;
    }

    // hàm này để lấy toàn bộ thông tin về khuyến mãi
    public static List<KHUYENMAI> getAllKM() {
        List<KHUYENMAI> listKM = new ArrayList<>();
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            ResultSet rs = stmt.executeQuery("select * from KhuyenMai");
            // show data
            while (rs.next()) {
                KHUYENMAI km = new KHUYENMAI(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getString(7));
                //System.out.println(rs.getString(1) + "  " + rs.getString(2)+ " "+ rs.getDouble(3) + " " +rs.getDouble(4)+ " " + rs.getDate(5)+ " " + rs.getDate(6)+ " " + rs.getString(7));
                listKM.add(km);
            }
            // close connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listKM;
    }
    //them 1 Khuyen mai

    public static void addKM(KHUYENMAI km) {
        String s1, s2, s7;
        Double s3, s4;
        Date ss5, ss6;
        s1 = km.getMaKM();
        s2 = km.getTenKM();
        s3 = km.getPhanTramKM();
        s4 = km.getDieuKienKM();
        s7 = km.getTinhTrang();

        ss5 = km.getNgayBatDau();
        String s5 = new SimpleDateFormat("yyyy-MM-dd").format(ss5);
        ss6 = km.getNgayKetThuc();
        String s6 = new SimpleDateFormat("yyyy-MM-dd").format(ss6);

        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            //("INSERT INTO KhuyenMai "
            //        + "VALUES( '" + s1 + "','" + s2 + "','" + s3 + "',' " + s4 + "','" + s5 + "','" + s6 + "'," + s7 + " )");
            stmt.executeUpdate("INSERT INTO KhuyenMai "
                    + "VALUES( '" + s1 + "','" + s2 + "','" + s3 + "',' " + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "' )");
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // sưat thong tin 1 KhuyenMai
    public static void updateKM(KHUYENMAI km, String key) {
        String s1, s2, s7;
        Double s3, s4;
        Date ss5, ss6;

        s1 = km.getMaKM();
        s2 = km.getTenKM();
        s3 = km.getPhanTramKM();
        s4 = km.getDieuKienKM();
        // chu y   ... 
        ss5 = km.getNgayBatDau();
        String s5 = new SimpleDateFormat("dd/MM/yyyy").format(ss5);

        ss6 = km.getNgayKetThuc();
        String s6 = new SimpleDateFormat("dd/MM/yyyy").format(ss6);
        s7 = km.getTinhTrang();

        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            //System.out.println();
            stmt.executeUpdate("DECLARE @date1 DATETIME, @date2 DATETIME,@value1 VARCHAR(10),@value2 VARCHAR(10) "
                    + "SET @value1 = '" + s5 + "'" + "SET @value2 = '" + s6 + "' UPDATE KhuyenMai SET MaKM = '" + s1 + "', TenKM = '"
                    + s2 + "' , PhanTramKhuyenMai = '" + s3 + "',DieuKienKM = '" + s4 + "'"
                    + ",NgayBatDau = CONVERT(DATETIME,@value1,103)" + ",NgayKetThuc = CONVERT(DATETIME,@value2,103) WHERE MaKM = '" + key + "'");
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteKM(String key) {

        try {
            Connection conn = getConnection();
            // crate statement
            Statement sttm = conn.createStatement();
            sttm.executeUpdate("DELETE FROM KhuyenMai WHERE MaKM='" + key + "'");
            sttm.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("delete khuyen mai khong thanh cong");
        }

    }

    public static void main(String[] args) throws SQLException, ParseException {
        KHUYENMAI km = new KHUYENMAI();
        //KHUYENMAI mk = new KHUYENMAI("km06", "ctmuahexanh05" , 20.0 , 500.0 , "2020-3-22" , "2023-4-13" , "hoat dong" );
        //KHUY km = new KHUYENMAI(, , , ,"2020-3-22","2023-4-13","hoat dong");
        km.setMaKM("km07");
        km.setTenKM("ctmuahexanh05");
        km.setPhanTramKM(20.0);
        km.setDieuKienKM(500.0);
        km.setNgayBatDau("22/10/2022");
        km.setNgayKetThuc("15/11/2022");
        km.setTinhTrang("hoat dong");


        
        //deleteKM("km07");
        //updateKM(km , "km03");
        //getAllKM();
    }

}
