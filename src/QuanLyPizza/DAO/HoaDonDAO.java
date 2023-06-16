/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.HOADON;
import QuanLyPizza.Class.SANPHAM;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class HoaDonDAO {

    public static List<HOADON> showHoaDon() {

        ArrayList<HOADON> list = new ArrayList<>();
        try {
            String query = "SELECT MaHoaDon , ThanhTien ,Ten ,NgayLap FROM HoaDon  hd, NhanVien  nv where hd.MaNV=nv.MaNV";
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                HOADON l = new HOADON(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getDate(4));
                list.add(l);
                //System.out.println(rs.getString(1) + "        " + rs.getDouble(2) + "       " + rs.getString(3) + "      " + rs.getDate(4));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String coutDoanhThu() {
        String query = "SELECT sum(ThanhTien) FROM HoaDon";
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

    public static Double coutDoanhThuChart(int month) {
        String query = "SELECT sum(ThanhTien) FROM HoaDon where month(NgayLap)='" + month + "'";
        Double s = 0.0;
        try {
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                s = rs.getDouble(1);
                //System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public static void addHD(HOADON hd, String MaKM) {
        Date s6;
        s6 = hd.getNgaylap();
        String s6_1 = new SimpleDateFormat("yyyy-MM-dd").format(s6);

        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            if (MaKM.trim().equals("NULL")) {
//                System.out.println("INSERT INTO HoaDon "
//                        + "VALUES( '" + hd.getMaHD() + "'," + hd.getThanhTien() + ",NULL, '" + hd.getTen() + "','" + s6_1 + "' )");
                stmt.executeUpdate("INSERT INTO HoaDon "
                        + "VALUES( '" + hd.getMaHD() + "'," + hd.getThanhTien() + ",NULL, '" + hd.getTen() + "','" + s6_1 + "' )");
            } else {
//                System.out.println("INSERT INTO HoaDon "
//                        + "VALUES( '" + hd.getMaHD() + "'," + hd.getThanhTien() + ",'" + MaKM + "', '" + hd.getTen() + "','" + s6_1 + "' )");
                stmt.executeUpdate("INSERT INTO HoaDon "
                        + "VALUES( '" + hd.getMaHD() + "'," + hd.getThanhTien() + ",'" + MaKM + "', '" + hd.getTen() + "','" + s6_1 + "' )");
            }

            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //hàm này dùng để đém số phiếu nhập đã nhập vào dùng để tự động thiết lập mã hd
    public static int count_max() {
        int max = 0;
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT MaHoaDon FROM HoaDon");
            // show data
            while (rs.next()) {

                //System.out.println(rs.getString(1));
                String s = rs.getString(1);
                int h = Integer.parseInt(s.replace("hd", "").trim());
                if (max < h) {
                    max = h;
                } else {
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

    public static void main(String[] args) {
        //ArrayList<HOADON> list = (ArrayList<HOADON>) showHoaDon();
        //System.out.println(coutDoanhThu());
        //System.out.println(count_max());

    }

}
