/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyPizza.Class;

/**
 *
 * @author HP
 */
public class NHACUNGCAP {
    private String MaNCC;
    private String TenNCC;

    public NHACUNGCAP(String MaNCC, String TenNCC) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
    }

    public NHACUNGCAP() {
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }
    
    
}
