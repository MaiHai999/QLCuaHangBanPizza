/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HP
 */
public class NHANVIEN {
    private String MaNV;
    private String TenNV;
    private String GioiTinh;
    private Date NgaySinh;

    public NHANVIEN(String MaNV, String TenNV, String GioiTinh, Date NgaySinh) throws ParseException {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
    }

    public NHANVIEN() {
    }
    
    public String getMaNV() {
        return MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setNgaySinh(String NgaySinh) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(NgaySinh);
        this.NgaySinh = date;
    }
    
    

}
