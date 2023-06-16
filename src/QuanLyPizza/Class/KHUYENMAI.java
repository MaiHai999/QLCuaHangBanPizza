/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HP
 */
public class KHUYENMAI {
    private String MaKM;
    private  String TenKM;
    private Double PhanTramKM;
    private Double DieuKienKM;
    private Date  NgayBatDau;
    private Date NgayKetThuc;
    private String TinhTrang;

    public KHUYENMAI(String MaKM, String TenKM, Double PhanTramKM, Double DieuKienKM, Date NgayBatDau, Date NgayKetThuc, String TinhTrang)throws ParseException {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.PhanTramKM = PhanTramKM;
        this.DieuKienKM = DieuKienKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.TinhTrang = TinhTrang;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public Double getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(Double PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }

    public Double getDieuKienKM() {
        return DieuKienKM;
    }

    public void setDieuKienKM(Double DieuKienKM) {
        this.DieuKienKM = DieuKienKM;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

//    public void setNgayBatDau(Date NgayBatDau) {
//       this.NgayBatDau = NgayBatDau;
//    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

//    public void setNgayKetThuc(Date NgayKetThuc) {
//        this.NgayKetThuc = NgayKetThuc;
//    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

  

    public void setNgayBatDau(String NgayBatDau)throws ParseException {
        
          Date date = new SimpleDateFormat("dd/MM/yyyy").parse( NgayBatDau);
     this.NgayBatDau  = date;
 }

    public void setNgayKetThuc(String NgayKetThuc)throws ParseException {
     
         Date date = new SimpleDateFormat("dd/MM/yyyy").parse( NgayKetThuc);
     this.NgayKetThuc  = date;
   }

   

    @Override
    public String toString() {
        return "KHUYENMAI{" + "MaKM=" + MaKM + ", TenKM=" + TenKM + ", PhanTramKM=" + PhanTramKM + ", DieuKienKM=" + DieuKienKM + ", NgayBatDau=" + NgayBatDau + ", NgayKetThuc=" + NgayKetThuc + ", TinhTrang=" + TinhTrang + '}';
    }

    public KHUYENMAI() {
    }

   
           
    
}
