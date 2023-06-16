/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.DAO;


import QuanLyPizza.Class.SANPHAM;
import static QuanLyPizza.Connect.MyConnect.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.sql.PreparedStatement;
import java.util.Iterator;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author HP
 */
public class SanPhamDAO {
    
    public static void main(String[] args) throws IOException{
        //updateTBSP("100" , "sp03");
        //System.out.println(coutSP());
        
       
//        ArrayList<SANPHAM> listSP = importExel("C:\\Users\\HP\\Desktop\\SanPham.xls"); 
//        for(SANPHAM sp1 : listSP){
//            System.out.println(sp1.getMaSP());
//        }




    }
    
    //hàm này dừng để quét toàn bộ dữ liệu từ database vào list
    public static List<SANPHAM> getAllSP(){
        List<SANPHAM> listSP = new ArrayList<>(); 
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select * from SanPham");
             // show data
             while (rs.next()){
                SANPHAM sp = new SANPHAM(rs.getString(1), rs.getString(2) , rs.getString(3) ,rs.getDouble(4) , rs.getInt(5), rs.getString(6));
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
    
    public static List<SANPHAM> searchProduct(ArrayList<SANPHAM> listSP,String key){
        List<SANPHAM> listSP1 = new ArrayList<>();
        for(SANPHAM sp : listSP){
            if(sp.getTen_SP().trim().contains(key)){
                listSP1.add(sp);
            }
        }
        return listSP1;
    }
    
    public static void addSP(SANPHAM sp){
            String s1 ,s2,s3,s6;
            s1 = sp.getMaSP();
            s2 = sp.getTen_SP();
            s3 = sp.getDon_vi_tinh();
            s6 = sp.getMaLoai();
            int s5 = sp.getSoLuongConLai();
            double s4 = sp.getGia();
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             stmt.executeUpdate("INSERT INTO SanPham(MaSP , TenSP , DonViTinh , GiaBan , SoLuongSPCon , MaLoai) "
                     + "VALUES( '" +s1 + "','" +s2 + "','" + s3 +"'," +s4 +"," + s5 +",'" + s6 + "' )" );
             
            // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void updateSP(SANPHAM sp , String key){
            String s1 ,s2,s3,s6;
            s1 = sp.getMaSP();
            s2 = sp.getTen_SP();
            s3 = sp.getDon_vi_tinh();
            s6 = sp.getMaLoai();
            int s5 = sp.getSoLuongConLai();
            double s4 = sp.getGia();
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
            // System.out.println("UPDATE SanPham SET MaSP = '" + s1 + "', TenSP = '" +s2 +"' , DonViTinh = '" + s3 +"'"
            //         + ",GiaBan = "+s4 +" ,SoLuongSPCon = "+s5+" , MaLoai = '"+s6+"' WHERE MaSP = '"+ key +"'");
             stmt.executeUpdate("UPDATE SanPham SET MaSP = '" + s1 + "', TenSP = '" +s2 +"' , DonViTinh = '" + s3 +"'"
                     + ",GiaBan = "+s4 +" ,SoLuongSPCon = "+s5+" , MaLoai = '"+s6+"' WHERE MaSP = '"+ key +"'" );
             // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void deleteSP(String key){
        try{
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             stmt.executeUpdate("DELETE FROM SanPham where MaSP = '"+ key +"'" );
             // close connection
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void exportExel(String filename1) throws FileNotFoundException, IOException{

        ArrayList<SANPHAM> listSP = (ArrayList<SANPHAM>) getAllSP(); 
        //khai báo tên file muốn tạo
        String filename = filename1 + ".xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        //gọi phương thức creatSheet() và truyền tên file muốn tạo
        HSSFSheet sheet = workbook.createSheet("January");
        //tạo hàng thứ 0 sử dụng phương thức createRow()
        HSSFRow rowhead = sheet.createRow( 0);
        rowhead.createCell(0).setCellValue("MaSP");
        rowhead.createCell(1).setCellValue("TenSP");
        rowhead.createCell(2).setCellValue("MaLoai");
        rowhead.createCell(3).setCellValue("DonViTinh");
        rowhead.createCell(4).setCellValue("SoLuong");
        rowhead.createCell(5).setCellValue("DonGia");
        int index = 1;
        for(SANPHAM sp : listSP){
            HSSFRow rowhead1 = sheet.createRow( index);
            index = index + 1;
            insert(rowhead1 ,sp);
            
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
    
    public static void insert(HSSFRow rowhead , SANPHAM sp){
        rowhead.createCell(0).setCellValue(sp.getMaSP());
        rowhead.createCell(1).setCellValue(sp.getTen_SP());
        rowhead.createCell(2).setCellValue(sp.getMaLoai());
        rowhead.createCell(3).setCellValue(sp.getDon_vi_tinh());
        rowhead.createCell(4).setCellValue(sp.getSoLuongConLai());
        rowhead.createCell(5).setCellValue(sp.getGia());
    }
    
    public static ArrayList<SANPHAM> importExel(String filePath) throws FileNotFoundException, IOException{
        
       ArrayList<SANPHAM> listSP = new ArrayList<SANPHAM>();
        
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
           if(index != 0){
               
                // Lấy Iterator cho tất cả các cell của dòng hiện tại.
                Iterator<Cell> cellIterator = row.cellIterator();
                SANPHAM sp = new SANPHAM();
           
                while (cellIterator.hasNext()) {
                     
                    Cell cell = cellIterator.next();
 
                    // Đổi thành getCellType() nếu sử dụng POI 4.x
                    CellType cellType = cell.getCellTypeEnum();
                    int columnIndex = cell.getColumnIndex();
               
                    switch(columnIndex){
                        case 0:
                            sp.setMaSP(cell.getStringCellValue()); 
                            break;
                        case 1:
                            sp.setTen_SP(cell.getStringCellValue());
                            break;
                        case 2:
                            sp.setMaLoai(cell.getStringCellValue());
                            break;
                        case 3:
                            sp.setDon_vi_tinh(cell.getStringCellValue());
                            break;
                        case 4:
                            sp.setSoLuongConLai((int) cell.getNumericCellValue());
                            break;
                        case 5:
                            sp.setGia(cell.getNumericCellValue());
                            break;
                    }
               
                }
                listSP.add(sp);
               
            }
            index = index + 1;
           
       }
       return listSP;
    }
    
    public static String getSPKey(String key){
        String s = null;
        try{
            
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select TenSP from SanPham WHERE MaSP = '" + key +"'");
             // show data
             while (rs.next()){
                s = rs.getString(1);
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
    
    public static int getSLSPKey(String key){
        int s = 0;
        try{
            
             Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             // get data from table 
             ResultSet rs = stmt.executeQuery("select SoLuongSPCon from SanPham WHERE MaSP = '" + key +"'");
             // show data
             while (rs.next()){
                s = rs.getInt(1);
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
    
    //hàm này dùng để cập nhật lại số sản phẩm còn(khi bán hàng thì trừ đi số sản phẩm còn)
    public static void updateTBSP(String a, String b) {
        
        String query = "update SanPham set SoLuongSPCon = '" + a + "' where MaSP='" + b + "' ";

        try {
            Connection conn = getConnection();
             // crate statement
             Statement stmt = conn.createStatement();
             stmt.executeUpdate(query);
             // close connection
            stmt.close();
            conn.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    //hàm này để đếm số sản phẩm
    public static String coutSP() {
        String query = "SELECT count(MaSP) FROM SanPham";
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
    
    
    
