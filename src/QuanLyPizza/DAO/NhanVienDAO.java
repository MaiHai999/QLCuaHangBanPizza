/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;

import QuanLyPizza.Class.NHANVIEN;
import QuanLyPizza.Class.SANPHAM;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import static QuanLyPizza.DAO.SanPhamDAO.getAllSP;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author HP
 */
public class NhanVienDAO {

    public static void main(String[] args) throws ParseException, IOException {
        //System.out.println(coutNV());
//        Date date = new Date();
//        String s5 = new SimpleDateFormat("dd/MM/yyyy").format(date);
//        //(s5);
//
////          ArrayList<NHANVIEN> listSP = importExelNV("C:\\Users\\HP\\Desktop\\NhanVien.xls");
////          for(NHANVIEN sp : listSP){
////            System.out.println(sp.getMaNV());
////          }
    }

    //hàm này dừng để quét toàn bộ dữ liệu từ database vào list
    public static List<NHANVIEN> getAllNV() {
        List<NHANVIEN> listNV = new ArrayList<>();
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            ResultSet rs = stmt.executeQuery("select * from NhanVien");
            // show data
            while (rs.next()) {
                NHANVIEN nv = new NHANVIEN(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
                //System.out.println(rs.getDate(4)+ "  " + rs.getString(1));
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

    /**
     *
     * @param listNV
     * @param key
     * @return
     */
    public static List<NHANVIEN> searchNV(ArrayList<NHANVIEN> listNV, String key) {
        List<NHANVIEN> listNV1 = new ArrayList<>();
        for (NHANVIEN nv : listNV) {
            if (nv.getTenNV().trim().contains(key.trim())) {
                listNV1.add(nv);
            }
        }
        return listNV1;
    }

    public static void addNV(NHANVIEN nv) {
        String s1, s2, s3;
        s1 = nv.getMaNV();
        s2 = nv.getTenNV();
        s3 = nv.getGioiTinh();
        Date s4 = nv.getNgaySinh();
        String s5 = new SimpleDateFormat("yyyy-MM-dd").format(s4);
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            // System.out.println("INSERT INTO NhanVien "
            //         + "VALUES( '" +s1 + "','" +s2 + "','" + s3 +"', '" + s5 + "' )");
            stmt.executeUpdate("INSERT INTO NhanVien "
                    + "VALUES( '" + s1 + "','" + s2 + "','" + s3 + "', '" + s5 + "' )");

            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void updateNV(NHANVIEN nv, String key) {
        String s1, s2, s3;
        s1 = nv.getMaNV();
        s2 = nv.getTenNV();
        s3 = nv.getGioiTinh();
        Date s4 = nv.getNgaySinh();
        String s5 = new SimpleDateFormat("dd/MM/yyyy").format(s4);
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            //System.out.println();
            stmt.executeUpdate("DECLARE @date DATETIME,@value VARCHAR(10) "
                    + "SET @value = '" + s5 + "' UPDATE NhanVien SET MaNV = '" + s1 + "', Ten = '" + s2 + "' , GioiTinh = '" + s3 + "'"
                    + ",NamSinh = CONVERT(DATETIME,@value,103) WHERE MaNV = '" + key + "'");
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteNV(String key) {
        try {
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 
            stmt.executeUpdate("DELETE FROM NhanVien where MaNV = '" + key + "'");
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void exportExelNV(String filename1) throws FileNotFoundException, IOException {

        ArrayList<NHANVIEN> listSP = (ArrayList<NHANVIEN>) getAllNV();
        //khai báo tên file muốn tạo
        String filename = filename1 + ".xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        //gọi phương thức creatSheet() và truyền tên file muốn tạo
        HSSFSheet sheet = workbook.createSheet("January");
        //tạo hàng thứ 0 sử dụng phương thức createRow()
        HSSFRow rowhead = sheet.createRow(0);
        rowhead.createCell(0).setCellValue("MaNV");
        rowhead.createCell(1).setCellValue("TenNV");
        rowhead.createCell(2).setCellValue("GioiTinh");
        rowhead.createCell(3).setCellValue("NamSinh");
        int index = 1;
        for (NHANVIEN sp : listSP) {
            HSSFRow rowhead1 = sheet.createRow(index);
            index = index + 1;
            insertNV(rowhead1, sp);

        }

        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        //đóng stream
        fileOut.close();
        //đóng workbook
        workbook.close();
        //in thông báo tạo thành công

        System.out.println("File Excel đã được tạo thành công.");

    }

    public static void insertNV(HSSFRow rowhead, NHANVIEN sp) {
        rowhead.createCell(0).setCellValue(sp.getMaNV());
        rowhead.createCell(1).setCellValue(sp.getTenNV());
        rowhead.createCell(2).setCellValue(sp.getGioiTinh());
        String s5 = new SimpleDateFormat("dd/MM/yyyy").format(sp.getNgaySinh());
        rowhead.createCell(3).setCellValue(s5);
    }

    public static ArrayList<NHANVIEN> importExelNV(String filePath) throws FileNotFoundException, IOException, ParseException {

        ArrayList<NHANVIEN> listSP = new ArrayList<NHANVIEN>();

        // Đọc một file XSL.
        FileInputStream inputStream = new FileInputStream(new File(filePath));

        // Đối tượng workbook cho file XSL.
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        // Lấy ra sheet đầu tiên từ workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        // Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
        Iterator<Row> rowIterator = sheet.iterator();
        int index = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (index != 0) {

                // Lấy Iterator cho tất cả các cell của dòng hiện tại.
                Iterator<Cell> cellIterator = row.cellIterator();
                NHANVIEN sp = new NHANVIEN();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    // Đổi thành getCellType() nếu sử dụng POI 4.x
                    CellType cellType = cell.getCellTypeEnum();

                    int columnIndex = cell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            sp.setMaNV(cell.getStringCellValue());
                            break;
                        case 1:
                            sp.setTenNV(cell.getStringCellValue());
                            break;
                        case 2:
                            sp.setGioiTinh(cell.getStringCellValue());
                            break;
                        case 3:
                            if (cellType == NUMERIC) {
                                Date temp = cell.getDateCellValue();
                                String s5 = new SimpleDateFormat("dd/MM/yyyy").format(temp);
                                sp.setNgaySinh(s5);
                            } else {
                                sp.setNgaySinh(cell.getStringCellValue());
                            }

                            break;
                    }

                }
                listSP.add(sp);

            }
            index = index + 1;

        }
        return listSP;
    }
    
    
    public static String coutNV() {
        String query = "SELECT count(MaNV) FROM NhanVien";
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

}
