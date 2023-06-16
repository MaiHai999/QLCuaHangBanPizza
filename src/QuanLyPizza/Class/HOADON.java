/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

import java.util.Date;

/**
 *
 * @author HUU77
 */
public class HOADON {
    private String MaHD;
    private double ThanhTien;
    private String Ten;
    private Date ngaylap;

    public HOADON(String MaHD, double ThanhTien, String Ten, Date ngaylap) {
        this.MaHD = MaHD;
        this.ThanhTien = ThanhTien;
        this.Ten = Ten;
        this.ngaylap = ngaylap;
    }

    public HOADON() {
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date ngaylap) {
        this.ngaylap = ngaylap;
    }

    
    
}
