/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.CHITIETHOADON;
import QuanLyPizza.Class.SANPHAM;
import QuanLyPizza.Class.TOPTHONGKE;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import static QuanLyPizza.DAO.HoaDonDAO.count_max;
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
public class ChiTietHoaDonDAO {

    public static List<TOPTHONGKE> limitTOP() {

        ArrayList<TOPTHONGKE> list = new ArrayList<>();
        try {
            String query = "SELECT TOP 5 MaSP , TONG = SUM(SoLuongSPSale) FROM ChiTietHoaDon GROUP BY MaSP ORDER BY TONG DESC";
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                TOPTHONGKE tk = new TOPTHONGKE(rs.getString(1), rs.getInt(2));
                list.add(tk);
                //System.out.println(tk.getTopSoLuong() + "     " + tk.getTopTenSp() );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<CHITIETHOADON> showChiTietHoaDon() {
        ArrayList<CHITIETHOADON> list = new ArrayList<>();
        try {
            String query = "SELECT cthd.MaHoaDon , cthd.MaSP , cthd.MaKH  , cthd.SoLuongSPSale , sp.GiaBan , hd.ThanhTien FROM ChiTietHoaDon cthd , SanPham sp , HoaDon hd WHERE cthd.MaHoaDon = hd.MaHoaDon and sp.MaSP = cthd.MaSP";
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                CHITIETHOADON l;
                l = new CHITIETHOADON(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6));
                list.add(l);
                //System.out.println(l.getMaHD() + " " + l.getThanhTien());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void addCTHD(SANPHAM sp, String MaHD, String MaKH) {
        String s2;
        int s3;
        s2 = sp.getMaSP();
        s3 = sp.getSoLuongConLai();

        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            if (MaKH.trim().length() != 0) {
//                System.out.println("INSERT INTO ChiTietHoaDon "
//                        + "VALUES( '" + MaHD + "','" + s2.trim() + "'," + s3 + ", '" + MaKH + "' )");
                stmt.executeUpdate("INSERT INTO ChiTietHoaDon "
                        + "VALUES( '" + MaHD + "','" + s2.trim() + "'," + s3 + ", '" + MaKH + "' )");
            } else {
//                System.out.println("INSERT INTO ChiTietHoaDon "
//                        + "VALUES( '" + MaHD + "','" + s2.trim() + "'," + s3 + ", NULL )");
                stmt.executeUpdate("INSERT INTO ChiTietHoaDon "
                        + "VALUES( '" + MaHD + "','" + s2.trim() + "'," + s3 + ", NULL )");
            }

            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<CHITIETHOADON> list = new ArrayList<>();
        list = (ArrayList<CHITIETHOADON>) showChiTietHoaDon();
    }
}
