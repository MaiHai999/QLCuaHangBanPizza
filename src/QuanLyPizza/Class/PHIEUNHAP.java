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
public class PHIEUNHAP {
    private String MaPN;
    private String MaSP;
    private String MaNCC;
    private double GiaCungCap;
    private int SoLuongNhap;
    private Date NgayNhap;
    private double ThanhTien;

    public PHIEUNHAP(String MaPN, String MaSP, String MaNCC, double GiaCungCap, int SoLuongNhap, Date NgayNhap, double ThanhTien) {
        this.MaPN = MaPN;
        this.MaSP = MaSP;
        this.MaNCC = MaNCC;
        this.GiaCungCap = GiaCungCap;
        this.SoLuongNhap = SoLuongNhap;
        this.NgayNhap = NgayNhap;
        this.ThanhTien = ThanhTien;
    }

    public PHIEUNHAP() {
    }

    public String getMaPN() {
        return MaPN;
    }

    public String getMaSP() {
        return MaSP;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public double getGiaCungCap() {
        return GiaCungCap;
    }

    public int getSoLuongNhap() {
        return SoLuongNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setGiaCungCap(double GiaCungCap) {
        this.GiaCungCap = GiaCungCap;
    }

    public void setSoLuongNhap(int SoLuongNhap) {
        this.SoLuongNhap = SoLuongNhap;
    }

    public void setNgayNhap(String NgayNhap) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(NgayNhap);
        this.NgayNhap = date;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
    
    
}
