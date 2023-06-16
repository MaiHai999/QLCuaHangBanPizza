/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

/**
 *
 * @author HUU77
 */
public class SANPHAM implements Comparable<SANPHAM> {

    private String MaSP;
    private String Ten_SP;
    private String Don_vi_tinh;
    private double Gia;
    private int SoLuongConLai;
    String MaLoai;

    public SANPHAM() {

    }

    public SANPHAM(String MaSP, String Ten_SP, String Don_vi_tinh, double Gia, int SoLuongConLai, String MaLoai) {
        this.MaSP = MaSP;
        this.Ten_SP = Ten_SP;
        this.Don_vi_tinh = Don_vi_tinh;
        this.Gia = Gia;
        this.SoLuongConLai = SoLuongConLai;
        this.MaLoai = MaLoai;
    }

    public SANPHAM(String MaSP, String Ten_SP, double Gia, int SoLuongConLai, String MaLoai) {
        this.MaSP = MaSP;
        this.Ten_SP = Ten_SP;
        this.Gia = Gia;
        this.SoLuongConLai = SoLuongConLai;
        this.MaLoai = MaLoai;
    }
    

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTen_SP() {
        return Ten_SP;
    }

    public void setTen_SP(String Ten_SP) {
        this.Ten_SP = Ten_SP;
    }

    public String getDon_vi_tinh() {
        return Don_vi_tinh;
    }

    public void setDon_vi_tinh(String Don_vi_tinh) {
        this.Don_vi_tinh = Don_vi_tinh;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public int getSoLuongConLai() {
        return SoLuongConLai;
    }

    public void setSoLuongConLai(int SoLuongConLai) {
        this.SoLuongConLai = SoLuongConLai;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getName() {
        String s = this.Ten_SP.trim();
        if (s.indexOf(" ") >= 0) {
            int vt = s.lastIndexOf(" ");
            return s.substring(vt + 1);
        } else {
            return s;
        }
    }

    @Override
    public int compareTo(SANPHAM o) {
        String s = this.getName();
        String tenO = o.getName();
        return s.compareTo(tenO);
    }
    
    
    

}
