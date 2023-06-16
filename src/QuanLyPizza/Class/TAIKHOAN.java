/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

/**
 *
 * @author HUU77
 */
public class TAIKHOAN {
    private String MaTK;
    private String TEN_TAI_KHOAN;
    private String MAT_KHAU;
    private String MaNV;

    public TAIKHOAN() {
    }

    public TAIKHOAN(String MaTK, String TEN_TAI_KHOAN, String MAT_KHAU, String MaNV) {
        this.MaTK = MaTK;
        this.TEN_TAI_KHOAN = TEN_TAI_KHOAN;
        this.MAT_KHAU = MAT_KHAU;
        this.MaNV = MaNV;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getTEN_TAI_KHOAN() {
        return TEN_TAI_KHOAN;
    }

    public void setTEN_TAI_KHOAN(String TEN_TAI_KHOAN) {
        this.TEN_TAI_KHOAN = TEN_TAI_KHOAN;
    }

    public String getMAT_KHAU() {
        return MAT_KHAU;
    }

    public void setMAT_KHAU(String MAT_KHAU) {
        this.MAT_KHAU = MAT_KHAU;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }
    
}
