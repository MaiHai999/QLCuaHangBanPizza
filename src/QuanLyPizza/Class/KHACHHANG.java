/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

/**
 *
 * @author lenovo
 */
public class KHACHHANG implements Comparable<KHACHHANG> {

    private String MaKH;
    private String TenKH;
    private String GioiTinh;
    private double TongChiTieu;

    public KHACHHANG() {
    }

    public KHACHHANG(String MaKH, String TenKH, String GioiTinh, double TongChiTieu) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.GioiTinh = GioiTinh;
        this.TongChiTieu = TongChiTieu;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public double getTongChiTieu() {
        return TongChiTieu;
    }

    public void setTongChiTieu(double TongChiTieu) {
        this.TongChiTieu = TongChiTieu;
    }

    public String getName() {
        String s = this.TenKH.trim();
        if (s.indexOf(" ") >= 0) {
            int vt = s.lastIndexOf(" ");
            return s.substring(vt + 1);
        } else {
            return s;
        }
    }

    @Override
    public int compareTo(KHACHHANG o) {
        String s = this.getName();
        String tenO = o.getName();
        return s.compareTo(tenO);
    }
}
