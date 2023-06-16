/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

/**
 *
 * @author HUU77
 */
public class CHITIETHOADON {

    private String MaHD;
    private String MaSP;
    private String MaKH;
    private int SoLuong;
    private double Gia;
    private double ThanhTien;

    public CHITIETHOADON(String MaHD, String MaSP, String MaKH, int SoLuong, double Gia, double ThanhTien) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.MaKH = MaKH;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.ThanhTien = ThanhTien;
    }

    public CHITIETHOADON() {
    }

    public String getMaHD() {
        return MaHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public String getMaKH() {
        return MaKH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public double getGia() {
        return Gia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
 

}
